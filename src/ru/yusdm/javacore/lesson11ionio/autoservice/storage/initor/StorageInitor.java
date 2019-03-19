package ru.yusdm.javacore.lesson11ionio.autoservice.storage.initor;


import ru.yusdm.javacore.lesson11ionio.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson11ionio.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11ionio.autoservice.storage.initor.datasourcereader.DataSourceIoTxtFileFromResourcesReader;
import ru.yusdm.javacore.lesson11ionio.autoservice.storage.initor.datasourcereader.DataSourceReader;

import java.util.ArrayList;
import java.util.List;

public class StorageInitor {

    private MarkService markService;

    public StorageInitor(MarkService markService) {
        this.markService = markService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE, JSON_FILE
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

        List<Mark> marks = new ArrayList<>();
        DataSourceReader<List<Mark>> dataSourceReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataSourceReader = new DataSourceIoTxtFileFromResourcesReader();
                break;
            }
            case XML_FILE: {
                break;
            }
            case JSON_FILE: {
                break;
            }
        }

        if (dataSourceReader != null) {
            marks = dataSourceReader.getDataFromFile(filePath);
        }

        return marks;
    }

}
