package ru.yusdm.javacore.lesson10.genericscontinue;

/**
 * Created by Admin on 3/11/2019.
 */
public interface BaseCrud<T, ID> {
    void save(T entity);
    T getEntityById(ID id);
}
