package ru.yusdm.javacore.lesson24web.autoservice.mark.repo.impl.jdbc;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.OrderDirection;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.OrderType;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson24web.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.jdbc.ModelMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MarkDefaultRepoImpl implements MarkRepo {

    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("COUNTRY", "NAME");

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        try {
            //SELECT * FROM PERSON WHERE (AGE = ?) AND (NAME = ?)
            //ps.set(1, 30)
            //ps.set(2, "Dima")
            //ps.set(10, "Petr")

            //SELECT * FROM PERSON WHERE (AGE = :age) AND (NAME = :name)
            //map.put("age", 30)
            //map.put("name", "Dima")

            //SELECT * FROM PERSON WHERE (AGE = ?) AND (NAME = ?)

            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

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

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(MarkSearchCondition searchCondition) {
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
            String whereStr = String.join(" AND ", where);
            //(NAME =?) AND (COUNTRY = ?)
            //SELECT * FROM MARK WHERE (NAME =?) AND (COUNTRY = ?)
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getOrdering(searchCondition);
            //SELECT * FROM MARK WHERE (NAME =?) AND (COUNTRY = ?) ORDER BY NAME,COUNTRY ASC

            if (searchCondition.shouldPaginate()){
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(MarkSearchCondition markSearchCondition){
        return " LIMIT " + markSearchCondition.getPaginator().getLimit() + " OFFSET " + markSearchCondition.getPaginator().getOffset();
    }

    private String getOrdering(MarkSearchCondition searchCondition) {

        if (searchCondition.needOrdering()) {
            OrderType orderType = searchCondition.getOrderType();
            OrderDirection orderDirection = searchCondition.getOrderDirection();

            switch (orderType) {

                case SIMPLE: {
                    return " ORDER BY " + searchCondition.getOrderByField().name() + " " + orderDirection;
                    //ORDER BY NAME ASC/DESC
                }
                case COMPLEX: {
                    return " ORDER BY " + String.join(", ", COMPLEX_ORDER_FIELDS) + " " + orderDirection;
                    // ORDER BY NAME,COUNTRY ASC/DESC
                }
            }
        }

        return "";
    }

    @Override
    public Mark insert(Mark mark) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertMarkSql(),
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

    private String getInsertMarkSql() {
        return "INSERT INTO MARK ( NAME, COUNTRY ) VALUES (?, ?)";
    }

    private void appendPreparedStatementParamsForMark(AtomicInteger index, PreparedStatement ps, Mark mark) throws SQLException {
        ps.setString(index.incrementAndGet(), mark.getName());
        ps.setString(index.incrementAndGet(), mark.getCountry());
    }

    @Override
    public void insert(Collection<Mark> marks) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertMarkSql(), marks,
                    (ps, mark) -> appendPreparedStatementParamsForMark(new AtomicInteger(0), ps, mark));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Mark mark) {
        String sql = "UPDATE MARK SET NAME = ?, COUNTRY = ?, WHERE ID = ?";

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
            return QueryWrapper.selectOne(sql,
                    MarkMapper::mapMark,
                    ps -> {
                        ps.setLong(1, id);
                    });
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
        findAllMarksFetchingModels().forEach(System.out::println);
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

    @Override
    public List<Mark> findAllMarksFetchingModels() {
        try {
            String sql =
                    "SELECT \n" +
                            " mk.ID as MARK_IDENT, \n" +
                            " md.ID as MODEL_IDENT, \n" +
                            " mk.NAME as MARK_NAME, \n" +
                            " md.NAME as MODEL_NAME, \n" +
                            " mk.*, md.* \n" +
                            " \n" +
                            " FROM MARK mk \n" +
                            " LEFT JOIN MODEL md ON (mk.ID = md.MARK_ID)";
            /**
             *  1 | Vaz | 1 | 2106
             *  1 | Vaz | 2 | 21099
             *
             *  2 | BMW | 3 | X5
             *  2 | BMW | 4 | X3
             *
             *  3 | CAT | NULL | NULL
             */

            return QueryWrapper.select(sql, (rs, accumulator) -> {

                Map<Long, Mark> marksMap = new LinkedHashMap<>();

                while (rs.next()) {
                    long markId = rs.getLong("MARK_IDENT");

                    if (!marksMap.containsKey(markId)) {
                        Mark mark = getMarkForFindAllMarksFetchingModelsQuery(rs, markId);
                        marksMap.put(markId, mark);
                        accumulator.add(mark);
                    }

                    Mark mark = marksMap.get(markId);

                    String discrStr = rs.getString("DISCRIMINATOR");
                    if (discrStr != null) {
                        Model model = getModelForFindAllMarksFetchingModelsQuery(rs, discrStr);

                        if (mark.getModels() == null) {
                            mark.setModels(new ArrayList<>());
                        }
                        mark.getModels().add(model);
                    }
                }
            });

        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private Mark getMarkForFindAllMarksFetchingModelsQuery(ResultSet rs, long markId) throws SQLException{
        Mark mark = MarkMapper.mapMark(rs);
        mark.setId(markId);
        mark.setName(rs.getString("MARK_NAME"));
        return mark;
    }

    private Model getModelForFindAllMarksFetchingModelsQuery(ResultSet rs, String discrStr) throws SQLException{
        ModelDiscriminator discriminator = ModelDiscriminator.valueOf(discrStr);

        Model model;
        if (ModelDiscriminator.PASSENGER.equals(discriminator)) {
            model = ModelMapper.mapPassenger(rs);
        } else {
            model = ModelMapper.mapTruck(rs);
        }

        ModelMapper.mapCommonModelData(model, rs);
        model.setId(rs.getLong("MODEL_IDENT"));
        model.setName(rs.getString("MODEL_NAME"));

        return model;
    }
}
