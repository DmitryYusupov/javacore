package ru.yusdm.javacore.lesson22database.lesson.connect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionPool implements Connectable{

    private static HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();
        config.setUsername("sa");
        config.setJdbcUrl("jdbc:h2:file:C:\\Users\\Admin\\Desktop\\h2");

        dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

}
