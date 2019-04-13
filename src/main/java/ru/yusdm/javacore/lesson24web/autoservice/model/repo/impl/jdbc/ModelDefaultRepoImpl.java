package ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.jdbc;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.database.datasource.HikariCpDataSource;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.KeyGenerationError;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson24web.autoservice.model.exception.unchecked.UnknownModelDiscriminatorException;
import ru.yusdm.javacore.lesson24web.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson24web.autoservice.model.search.ModelSearchCondition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.INTEGER;
import static ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator.PASSENGER;
import static ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.jdbc.ModelMapper.mapPassenger;
import static ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.jdbc.ModelMapper.mapTruck;

public class ModelDefaultRepoImpl implements ModelRepo {

    @Override
    public List<? extends Model> search(ModelSearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return ModelDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> PASSENGER.equals(discriminator) ? mapPassenger(rs) : mapTruck(rs))
                                .orElseThrow(UnknownModelDiscriminatorException::new);
                    },
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

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(ModelSearchCondition searchCondition) {
        StringBuilder sql = new StringBuilder("SELECT * FROM MODEL");

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql.append(" WHERE ID = ?");
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            StringBuilder conditions = new StringBuilder();

            if (searchCondition.getModelDiscriminator() != null) {
                conditions.append(" WHERE (DISCRIMINATOR = ?)");
                psConsumers.add(ps -> ps.setString(1, searchCondition.getModelDiscriminator().toString()));
            }
            sql.append(conditions.toString());
        }

        return new SqlPreparedStatementConsumerHolder(sql.toString(), psConsumers);
    }


    @Override
    public Model insert(Model model) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertModelSql(),
                    ps -> {
                        appendPreparedStatementParamsForModel(ps, model, new AtomicInteger(0));
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                model.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return model;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private String getInsertModelSql() {
        return "INSERT INTO MODEL (" +
                "MARK_ID," +
                "NAME," +
                "DESCRIPTION," +
                "PRODUCTION_YEAR_START," +
                "PRODUCTION_YEAR_END," +
                "DISCRIMINATOR," +
                "NUMBER_OF_AIR_BAGS," +
                "NUMBER_OF_SEATS," +
                "AUDIO_SYSTEM_NAME," +
                "WEIGHT," +
                "EMBEDDED_KITCHEN," +
                "TANK_SIZE" +
                ")" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public void insert(Collection<Model> models) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertModelSql(), models,
                    (ps, model) -> appendPreparedStatementParamsForModel(ps, model, new AtomicInteger(0)));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Model model) {
        try {
            String sql =
                    "UPDATE MODEL " +
                            "SET " +
                            "MARK_ID = ?," +
                            "NAME = ? ," +
                            "DESCRIPTION = ?," +
                            "PRODUCTION_YEAR_START = ?," +
                            "PRODUCTION_YEAR_END = ?   ," +
                            "DISCRIMINATOR = ? ," +
                            "NUMBER_OF_AIR_BAGS = ? ," +
                            "NUMBER_OF_SEATS = ? ," +
                            "AUDIO_SYSTEM_NAME = ?," +
                            "WEIGHT = ?     ," +
                            "EMBEDDED_KITCHEN = ?," +
                            "TANK_SIZE = ?    " +
                            " WHERE ID = ? ";

            QueryWrapper.executeUpdate(sql, ps -> {
                AtomicInteger index = new AtomicInteger(0);
                appendPreparedStatementParamsForModel(ps, model, index);

                switch (model.getDiscriminator()) {

                    case PASSENGER: {
                        appendPreparedStatementParamsForPassengerModel(ps, (PassengerModel) model, index);
                        break;
                    }
                    case TRUCK: {
                        appendPreparedStatementParamsForTruckModel(ps, (TruckModel) model, index);
                        break;
                    }
                }
                ps.setLong(index.incrementAndGet(), model.getId());
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPreparedStatementParamsForModel(PreparedStatement ps, Model model, AtomicInteger index) throws SQLException {
        ps.setLong(index.incrementAndGet(), model.getMarkId());
        ps.setString(index.incrementAndGet(), model.getName());
        ps.setString(index.incrementAndGet(), model.getDescription());
        ps.setInt(index.incrementAndGet(), model.getProductionYearStart());
        ps.setInt(index.incrementAndGet(), model.getProductionYearEnd());
        ModelDiscriminator discriminator = model.getDiscriminator();
        ps.setString(index.incrementAndGet(), discriminator.toString());
    }

    private void appendPreparedStatementParamsForPassengerModel(PreparedStatement ps, PassengerModel passenger, AtomicInteger index) throws SQLException {
        ps.setInt(index.incrementAndGet(), passenger.getNumberOfAirbags());
        ps.setInt(index.incrementAndGet(), passenger.getNumberOfSeats());
        ps.setString(index.incrementAndGet(), passenger.getAudioSystemName());
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setNull(index.incrementAndGet(), BOOLEAN);
        ps.setNull(index.incrementAndGet(), INTEGER);
    }

    private void appendPreparedStatementParamsForTruckModel(PreparedStatement ps, TruckModel truck, AtomicInteger index) throws SQLException {
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setInt(index.incrementAndGet(), truck.getWeight());
        ps.setBoolean(index.incrementAndGet(), truck.isEmbeddedKitchen());
        ps.setInt(index.incrementAndGet(), truck.getTankSize());
    }

    @Override
    public Optional<Model> findById(Long id) {
        try {
            return QueryWrapper.selectOne("SELECT * FROM MODEL WHERE ID = ?",

                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return ModelDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> PASSENGER.equals(discriminator) ? mapPassenger(rs) : mapTruck(rs))
                                .orElseThrow(UnknownModelDiscriminatorException::new);
                    },

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
            QueryWrapper.executeUpdate("DELETE FROM MODEL WHERE ID = ?", ps -> {
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
    public List<Model> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM MODEL",
                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return ModelDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> PASSENGER.equals(discriminator) ? mapPassenger(rs) : mapTruck(rs))
                                .orElseThrow(UnknownModelDiscriminatorException::new);
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM MODEL",
                    rs -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Model> getModelsByMarkId(long markId) {
        try {
            String sql = "SELECT * FROM MODEL WHERE MARK_ID = ?";
            return QueryWrapper.select(sql,
                    rs -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return ModelDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> PASSENGER.equals(discriminator) ? mapPassenger(rs) : mapTruck(rs))
                                .orElseThrow(UnknownModelDiscriminatorException::new);
                    },
                    ps -> {
                        ps.setLong(1, markId);
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private Connection getConnection() {
        return HikariCpDataSource.getInstance().getConnection();
    }
}
