package ru.yusdm.javacore.lesson17java8.lesson.java8.functionalinterface;

/**
 * Created by Admin on 3/29/2019.
 */
@FunctionalInterface
public interface MegaExtractor extends Extractor {

    void extract();

    default void megaExtract() {
    }
}
