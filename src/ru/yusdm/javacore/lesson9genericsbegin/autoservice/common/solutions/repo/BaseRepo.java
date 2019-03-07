package ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.repo;

public interface BaseRepo<TYPE, ID> {

    void insert(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void printAll();
}
