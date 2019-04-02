package ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.parser;

@FunctionalInterface
public interface FileParser<EXTRACTED_DATA> {
    EXTRACTED_DATA parseFile(String file) throws Exception;
}
