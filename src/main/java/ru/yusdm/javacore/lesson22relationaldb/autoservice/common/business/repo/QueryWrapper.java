package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.repo;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.database.datasource.HikariCpDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class QueryWrapper {

    public static <T> List<T> select(String sql, Function<ResultSet, T> mapper) throws Exception {

        try (Connection connection = HikariCpDataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapper.apply(resultSet));
            }

            return result;
        }
    }

    public static <T> Optional<T> selectOne(String sql, Function<ResultSet, T> mapper) throws Exception {

        try (Connection connection = HikariCpDataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(mapper.apply(resultSet));
            } else {
                return Optional.empty();
            }
        }
    }

}
