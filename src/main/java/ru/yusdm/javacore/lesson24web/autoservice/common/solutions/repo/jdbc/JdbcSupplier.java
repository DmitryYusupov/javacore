package ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.jdbc;

public interface JdbcSupplier<T> {
    T get() throws Exception;
}
