package ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.datasourcereader;

public interface DataSourceReader<EXTRACTED_DATA> {
    EXTRACTED_DATA getDataFromFile(String file) throws Exception;
}
