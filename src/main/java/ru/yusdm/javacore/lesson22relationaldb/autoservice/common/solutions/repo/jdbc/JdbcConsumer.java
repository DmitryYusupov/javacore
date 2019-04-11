package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcConsumer<T> {

    void consume(T t) throws Exception;
}
