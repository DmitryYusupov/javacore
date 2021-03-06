package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface ResultSetToExtractor<EXTRACT_TO> {
    void extract(ResultSet rs, List<EXTRACT_TO> accumulator) throws SQLException;
}
