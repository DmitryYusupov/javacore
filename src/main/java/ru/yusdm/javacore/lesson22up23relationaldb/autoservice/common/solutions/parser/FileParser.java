package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.parser;

@FunctionalInterface
public interface FileParser<EXTRACTED_DATA> {
    EXTRACTED_DATA parseFile(String file) throws Exception;
}
