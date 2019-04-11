package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.repo.jdbc.SqlPreparedStatemntConsumerHolder;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.search.OrderDirection;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.search.OrderType;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.search.MarkSearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MarkDefaultRepoImpl implements MarkRepo {

    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("COUNTRY", "NAME");

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        try {
            SqlPreparedStatemntConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    MarkMapper::mapMark,
                    ps -> {
                        List<PreparedStatementConsumer> psConsumers = sqlParamsHolder.getPreparedStatementConsumers();
                        for (PreparedStatementConsumer consumer : psConsumers) {
                            consumer.consume(ps);
                        }
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private SqlPreparedStatemntConsumerHolder getSearchSqlAndPrStmtHolder(MarkSearchCondition searchCondition) {
        String sql = "SELECT * FROM MARK";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();
            if (searchCondition.searchByCountry()) {
                where.add("(COUNTRY = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getCountry()));
            }

            if (searchCondition.searchByName()) {
                where.add("(NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getName()));
            }
            String whereStr = String.join("AND ", where);

            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getOrdering(searchCondition);
        }

        return new SqlPreparedStatemntConsumerHolder(sql, psConsumers);
    }

    private String getOrdering(MarkSearchCondition searchCondition) {

        if (searchCondition.needOrdering()) {
            OrderType orderType = searchCondition.getOrderType();
            OrderDirection orderDirection = searchCondition.getOrderDirection();

            switch (orderType) {

                case SIMPLE: {
                    return " ORDER BY " + searchCondition.getOrderByField().name() + " " + orderDirection;
                }
                case COMPLEX: {
                    return " ORDER BY " + String.join(", ", COMPLEX_ORDER_FIELDS) + " " + orderDirection;
                }
            }
        }

        return "";
    }

    @Override
    public Mark insert(Mark mark) {
        String sql = "INSERT INTO MARK (" +
                "NAME," +
                "COUNTRY" +
                ")" +
                "VALUES (?, ?)";

        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(sql,
                    ps -> {
                        appendPreparedStatementParamsForMark(new AtomicInteger(0), ps, mark);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                mark.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return mark;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPreparedStatementParamsForMark(AtomicInteger index, PreparedStatement ps, Mark mark) throws SQLException {
        ps.setString(index.incrementAndGet(), mark.getName());
        ps.setString(index.incrementAndGet(), mark.getCountry());
    }

    @Override
    public void insert(Collection<Mark> marks) {
    }

    @Override
    public void update(Mark mark) {
        String sql = "UPDATE MARK SET " +
                "NAME = ?," +
                "COUNTRY = ?, " +
                "WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger();
                        appendPreparedStatementParamsForMark(index, ps, mark);
                        ps.setLong(index.incrementAndGet(), mark.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Mark> findById(Long id) {
        try {
            String sql = "SELECT * FROM MARK WHERE ID = ?";
            return QueryWrapper.selectOne(sql, MarkMapper::mapMark);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM MARK WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
    }

    @Override
    public List<Mark> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM MARK", MarkMapper::mapMark);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM MARK",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }

    }

}
