package ru.yusdm.javacore.lesson11io.autoservice.common.solutions.service;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceUncheckedException;

public interface BaseService<TYPE, ID> {

    void insert(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id) throws AutoServiceUncheckedException;

    void delete(TYPE entity);

    void printAll();

}

