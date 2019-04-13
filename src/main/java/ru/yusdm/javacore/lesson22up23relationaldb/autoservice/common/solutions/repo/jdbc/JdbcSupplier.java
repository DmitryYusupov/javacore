package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.repo.jdbc;

public interface JdbcSupplier<T> {
    T get() throws Exception;
}
