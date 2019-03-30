package ru.yusdm.javacore.lesson17java8.autoservice.common.solutions.repo;

import java.util.Collection;
import java.util.List;

public interface BaseRepo<TYPE, ID> {

    TYPE insert(TYPE entity);

    void insert(Collection<TYPE> items);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void printAll();

    List<TYPE> findAll();

    int countAll();
}
