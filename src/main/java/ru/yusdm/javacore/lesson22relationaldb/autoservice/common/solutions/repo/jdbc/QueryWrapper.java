package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.database.datasource.HikariCpDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryWrapper {

    public static <T> List<T> select(String sql, Connection connection, ResultSetExtractor<T> extractor) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractor.extract(resultSet));
            }

            return result;
        }
    }

    public static <T> List<T> select(String sql, ResultSetExtractor<T> extractor) throws SQLException {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection()) {
            return select(sql, connection, extractor);
        }
    }

    public static <T> List<T> select(String sql, Connection connection, ResultSetExtractor<T> mapper, PreparedStatementParamsConsumer psSupplier) throws Exception {

        try (PreparedStatement ps = psSupplier.applyParamsAndGet(connection.prepareStatement(sql));
             ResultSet resultSet = ps.executeQuery()) {

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapper.extract(resultSet));
            }

            return result;
        }
    }

    public static <T> List<T> select(String sql, ResultSetExtractor<T> mapper, PreparedStatementParamsConsumer psSupplier) throws Exception {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection()) {
            return select(sql, connection, mapper, psSupplier);
        }
    }

    public static <T> Optional<T> selectOne(String sql, Connection connection, ResultSetExtractor<T> extractor) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(extractor.extract(resultSet));
            } else {
                return Optional.empty();
            }
        }
    }

    public static <T> Optional<T> selectOne(String sql, ResultSetExtractor<T> extractor) throws SQLException {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection()) {
            return selectOne(sql, connection, extractor);
        }
    }

    public static <T> Optional<T> selectOne(String sql, Connection connection, ResultSetExtractor<T> extractor, PreparedStatementParamsConsumer psSupplier) throws SQLException {
        try (PreparedStatement ps = psSupplier.applyParamsAndGet(connection.prepareStatement(sql));
             ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(extractor.extract(resultSet));
            } else {
                return Optional.empty();
            }
        }
    }

    public static <T> Optional<T> selectOne(String sql, ResultSetExtractor<T> extractor, PreparedStatementParamsConsumer psSupplier) throws SQLException {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection()) {
            return selectOne(sql, connection, extractor, psSupplier);
        }
    }

    public static int executeUpdate(String sql, Connection connection, PreparedStatementParamsConsumer psSupplier) throws SQLException {
        try (PreparedStatement ps = psSupplier.applyParamsAndGet(connection.prepareStatement(sql))) {
            return ps.executeUpdate();
        }
    }

    public static int executeUpdate(String sql, PreparedStatementParamsConsumer psSupplier) throws SQLException {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection()) {
            return executeUpdate(sql, connection, psSupplier);
        }
    }


    public static <T> Optional<T> executeUpdateReturningGeneratedKey(String sql, Connection connection,
                                                                     PreparedStatementParamsConsumer psSupplier,
                                                                     ResultSetExtractor<T> keyExtractor) throws SQLException {
        try (PreparedStatement ps = psSupplier.applyParamsAndGet(connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))) {
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Optional.of(keyExtractor.extract(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public static <T> Optional<T> executeUpdateReturningGeneratedKey(String sql, PreparedStatementParamsConsumer psSupplier,
                                                                     ResultSetExtractor<T> keyExtractor) throws SQLException {
        try (Connection connection = HikariCpDataSource.getInstance().getConnection()) {
            return executeUpdateReturningGeneratedKey(sql, connection, psSupplier, keyExtractor);
        }
    }


}
