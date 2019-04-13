package ru.yusdm.javacore.lesson24web.autoservice.order.repo.impl.jdbc;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.ResultSetExtractor;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.repo.impl.jdbc.MarkMapper;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.jdbc.ModelMapper;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.repo.impl.jdbc.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderDefaultRepoImpl implements OrderRepo {

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder selectDataHolder = getSearchSqlAndPrStmtHolder(searchCondition);
            return QueryWrapper.select(selectDataHolder.getSql(), OrderMapper::mapOrder,
                    ps -> {
                        for (PreparedStatementConsumer statementConsumer : selectDataHolder.getPreparedStatementConsumers()) {
                            statementConsumer.consume(ps);
                        }
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(OrderSearchCondition searchCondition) {
        String sql = "SELECT * FROM ORDER_TAB ";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByMarkId()) {
                where.add("(MARK_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getMarkId()));
            }

            if (searchCondition.searchByModelId()) {
                where.add("(MODEL_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getModelId()));
            }

            if (searchCondition.searchByUserId()) {
                where.add("(USER_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getUserId()));
            }

            String whereStr = String.join("AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr);

            if (searchCondition.shouldPaginate()) {
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(OrderSearchCondition orderSearchCondition) {
        return " LIMIT " + orderSearchCondition.getPaginator().getLimit() + " OFFSET " + orderSearchCondition.getPaginator().getOffset();
    }


    @Override
    public int countByModel(long modelId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE MODEL_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, modelId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countByMark(long markId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE MARK_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, markId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteByUserId(long userId) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM ORDER_TAB WHERE USER_ID = ?",
                    ps -> {
                        ps.setLong(1, userId);
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) {
        try {
            String sql = "SELECT * FROM ORDER_TAB WHERE USER_ID = ?";
            return QueryWrapper.select(sql, OrderMapper::mapOrder, ps -> {
                ps.setLong(1, userId);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Order insert(Order order) {
        return insert(order, null);
    }

    @Override
    public Order insertTx(Order order, Connection connection) {
        return insert(order, connection);
    }

    private Order insert(Order order, Connection connection) {
        try {

            Optional<Long> optionalId;
            PreparedStatementConsumer psConsumer = ps -> appendPsValuesForInsertOrder(ps, order);
            ResultSetExtractor<Long> rsExtractor = rs -> rs.getLong("ID");

            if (connection == null) {
                optionalId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertOrderSql(), psConsumer, rsExtractor);
            } else {
                optionalId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertOrderSql(), connection, psConsumer, rsExtractor);
            }

            if (optionalId.isPresent()) {
                order.setId(optionalId.get());
            } else {
                throw new KeyGenerationError("ID");
            }
            return order;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPsValuesForInsertOrder(PreparedStatement ps, Order order) throws SQLException {
        int index = 0;
        ps.setLong(++index, order.getUser().getId());
        ps.setLong(++index, order.getMark().getId());
        ps.setLong(++index, order.getModel().getId());
        ps.setString(++index, order.getDescription());
        ps.setInt(++index, order.getPrice());
    }

    private String getInsertOrderSql() {
        return "INSERT INTO ORDER_TAB (USER_ID, MARK_ID, MODEL_ID, DESCRIPTION, PRICE) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void insert(Collection<Order> orders) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertOrderSql(), orders, this::appendPsValuesForInsertOrder);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("No update operation for order");
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            String sql = "SELECT * FROM ORDER_TAB WHERE ID = ?";
            return QueryWrapper.selectOne(sql, OrderMapper::mapOrder, ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Order> getFullOrder(long id) {
        try {
            String sql = "SELECT " +
                    "ordr.ID AS ORDER_IDENT, " +
                    "ordr.*, " +
                    "mk.ID AS MARK_IDENT, " +
                    "mk.NAME AS MARK_NAME, " +
                    "mk.*, " +
                    "md.ID AS MODEL_IDENT," +
                    "md.NAME AS MODEL_NAME, " +
                    "md.*, " +
                    "u.*, " +
                    "u.ID AS USER_IDENT " +
                    "FROM " +
                    "ORDER_TAB ordr " +
                    "INNER JOIN MARK mk ON (mk.ID = ordr.MARK_ID) " +
                    "INNER JOIN MODEL md ON (md.ID = ordr.MODEL_ID) " +
                    "INNER JOIN USER u ON (u.ID = ordr.USER_ID) " +
                    "WHERE ordr.ID = ?";

            return QueryWrapper.selectOne(sql, rs -> {
                Order order = OrderMapper.mapOrder(rs);
                order.setId(rs.getLong("ORDER_IDENT"));

                order.setMark(getMarkForOrderFullRequest(rs));
                order.setModel(getModelForOrderFullRequest(rs));
                order.setUser(getUserForOrderFullRequest(rs));
                return order;
            }, ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private User getUserForOrderFullRequest(ResultSet rs) throws SQLException {
        User user = UserMapper.mapUser(rs);
        user.setId(rs.getLong("USER_IDENT"));
        return user;
    }

    private Mark getMarkForOrderFullRequest(ResultSet rs) throws SQLException {
        Mark mark = MarkMapper.mapMark(rs);
        mark.setName(rs.getString("MARK_NAME"));
        mark.setId(rs.getLong("MARK_IDENT"));
        return mark;
    }

    private Model getModelForOrderFullRequest(ResultSet rs) throws SQLException {
        ModelDiscriminator discriminator = ModelDiscriminator.valueOf(rs.getString("DISCRIMINATOR"));
        Model model;
        if (ModelDiscriminator.PASSENGER.equals(discriminator)) {
            model = ModelMapper.mapPassenger(rs);
        } else {
            model = ModelMapper.mapTruck(rs);
        }
        model.setId(rs.getLong("MODEL_IDENT"));
        model.setName(rs.getString("MODEL_NAME"));

        return model;
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id, null);
    }

    @Override
    public void deleteByIdTx(long id, Connection connection) {
        deleteById(id, connection);
    }

    private void deleteById(Long id, Connection connection) {
        try {
            String sql = "DELETE FROM ORDER_TAB WHERE ID = ?";
            PreparedStatementConsumer psConsumer = ps -> ps.setLong(1, id);
            if (connection == null) {
                QueryWrapper.executeUpdate(sql, psConsumer);
            } else {
                QueryWrapper.executeUpdate(sql, connection, psConsumer);
            }
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
    }

    @Override
    public List<Order> findAll() {
        try {
            String sql = "SELECT * FROM ORDER_TAB ";
            return QueryWrapper.select(sql, OrderMapper::mapOrder);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }
}
