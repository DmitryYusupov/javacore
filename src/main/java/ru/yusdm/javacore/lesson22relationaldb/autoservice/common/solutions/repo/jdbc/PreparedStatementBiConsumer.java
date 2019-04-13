package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc;

import java.sql.PreparedStatement;

public interface PreparedStatementBiConsumer<T> extends JdbcBiConsumer<PreparedStatement, T> {
}
