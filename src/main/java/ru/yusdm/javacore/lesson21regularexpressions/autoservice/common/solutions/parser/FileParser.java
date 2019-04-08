package ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.parser;

@FunctionalInterface
public interface FileParser<EXTRACTED_DATA> {
    EXTRACTED_DATA parseFile(String file) throws Exception;
}
