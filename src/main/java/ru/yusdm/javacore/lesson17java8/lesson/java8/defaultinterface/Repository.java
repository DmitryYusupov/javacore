package ru.yusdm.javacore.lesson17java8.lesson.java8.defaultinterface;

/**
 * Created by Admin on 3/29/2019.
 */
public interface Repository<T> {
    void add(T t);
    T get();
    void delete(T t);

    default void update(T t){
        System.out.println("begin update");
    }
}
