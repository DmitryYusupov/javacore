package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementParamsConsumer {

    PreparedStatement applyParamsAndGet(PreparedStatement ps) throws SQLException;
}
