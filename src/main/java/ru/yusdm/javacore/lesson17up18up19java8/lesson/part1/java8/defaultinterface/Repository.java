package ru.yusdm.javacore.lesson17up18up19java8.lesson.part1.java8.defaultinterface;

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
