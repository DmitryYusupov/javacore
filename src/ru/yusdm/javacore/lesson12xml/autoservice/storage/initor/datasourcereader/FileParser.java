package ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.datasourcereader;

public interface FileParser<EXTRACTED_DATA> {
    EXTRACTED_DATA parseFile(String file) throws Exception;
}
