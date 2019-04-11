package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface PreparedStatementIdentityFunc {

    PreparedStatement applyParamsAndGet(PreparedStatement ps) throws Exception;
}
