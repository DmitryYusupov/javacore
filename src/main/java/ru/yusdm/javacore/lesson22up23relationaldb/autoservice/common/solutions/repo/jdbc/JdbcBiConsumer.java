package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcBiConsumer<T1, T2> {
    void consume(T1 t1, T2 t2) throws Exception;
}
