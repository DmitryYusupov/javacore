package ru.yusdm.javacore.lesson11ionio.autoservice.common.solutions.service;

import ru.yusdm.javacore.lesson11ionio.autoservice.common.business.exception.AutoServiceUncheckedException;

import java.util.List;

public interface BaseService<TYPE, ID> {

    void insert(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id) throws AutoServiceUncheckedException;

    void delete(TYPE entity);

    void printAll();

    List<TYPE> findAll();

}

