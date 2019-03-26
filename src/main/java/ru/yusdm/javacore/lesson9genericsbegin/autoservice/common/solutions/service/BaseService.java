package ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.service;

public interface BaseService<TYPE, ID> {

    void insert(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void delete(TYPE entity);

    void printAll();

}

