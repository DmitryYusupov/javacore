package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcConsumer<T> {

    void consume(T t) throws Exception;
}
