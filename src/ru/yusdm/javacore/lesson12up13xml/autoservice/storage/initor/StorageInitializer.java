package ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor;


import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader.FileParser;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader.MarksWithModelXmlStaxParser;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader.MarksWithModelsTxtFileParser;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader.MarksWithModelsXmlDomParser;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.datasourcereader.sax.MarksWithModelsXmlSaxParser;

import java.util.List;

public class StorageInitializer {

    private MarkService markService;

    public StorageInitializer(MarkService markService) {
        this.markService = markService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    public void initStorageWithMarksAndModels(String filePath, DataSourceType dataSourceType) throws Exception {
        List<Mark> marksToPersist = getMarksFromStorage(filePath, dataSourceType);

        if (!marksToPersist.isEmpty()) {
            for (Mark mark : marksToPersist) {
                markService.insert(mark);
            }
        }
    }

    private List<Mark> getMarksFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {

        FileParser<List<Mark>> dataSourceReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataSourceReader = new MarksWithModelsTxtFileParser();
                break;
            }

            case XML_FILE: {
                //dataSourceReader = new MarksWithModelsXmlDomParser();
                //dataSourceReader = new MarksWithModelXmlStaxParser();
                dataSourceReader = new MarksWithModelsXmlSaxParser();
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }

}
