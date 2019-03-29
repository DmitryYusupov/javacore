package ru.yusdm.javacore.lesson17java8.lesson.java8.functionalinterface;

@FunctionalInterface
public interface Extractor {

    void extract();

    default void extract2(){
    }
}
