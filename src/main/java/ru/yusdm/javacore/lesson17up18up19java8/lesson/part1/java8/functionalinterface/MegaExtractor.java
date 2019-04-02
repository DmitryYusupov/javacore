package ru.yusdm.javacore.lesson17up18up19java8.lesson.part1.java8.functionalinterface;

/**
 * Created by Admin on 3/29/2019.
 */
@FunctionalInterface
public interface MegaExtractor extends Extractor {

    void extract();

    default void megaExtract() {
    }
}
