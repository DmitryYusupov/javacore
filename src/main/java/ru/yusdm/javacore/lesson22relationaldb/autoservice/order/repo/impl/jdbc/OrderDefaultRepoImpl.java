package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.ResultSetExtractor;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.search.OrderSearchCondition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

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
        String sql = "SELECT * FROM ORDER";

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
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
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
            String sql = "SELECT FROM ORDER_TAB WHERE USER_ID = ?";
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
    public void deleteById(Long id) {
        deleteById(id, null);
    }

    @Override
    public void deleteByIdTx(long id, Connection connection) {
        deleteById(id, connection);
    }

    private void deleteById(Long id, Connection connection) {
        try {
            String sql = "DELETE FROM USER WHERE ID = ?";
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
            String sql = "SELECT * FROM ORDER_TAB";
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
