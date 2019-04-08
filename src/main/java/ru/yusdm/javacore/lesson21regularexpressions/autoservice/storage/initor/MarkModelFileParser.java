package ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.parser.FileParser;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor.datasourcereader.MarksWithModelXmlStaxParser;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.initor.datasourcereader.MarksWithModelsTxtFileParser;

import java.io.File;
import java.util.List;

public class MarkModelFileParser implements Runnable {

    private StorageInitializer.DataSourceType dataSourceType;
    private List<Mark> marks;
    private Thread thread;
    private File fileToParse;
    private volatile Exception parseException;

    public MarkModelFileParser(StorageInitializer.DataSourceType dataSourceType, File file) {
        this.dataSourceType = dataSourceType;
        thread = new Thread(this);
        fileToParse = file;
    }

    @Override
    public void run() {
        try {
            marks = getMarksFromStorage(fileToParse.getAbsolutePath(), dataSourceType);
        } catch (Exception e) {
            System.out.println("Error while parse file with marks");
            parseException = e;
        }
    }

    public synchronized List<Mark> getMarks() {
        return marks;
    }

    public void asyncParseMarks() {
        thread.start();
    }

    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }

    private List<Mark> getMarksFromStorage(String filePath, StorageInitializer.DataSourceType dataSourceType) throws Exception {

        FileParser<List<Mark>> dataSourceReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataSourceReader = new MarksWithModelsTxtFileParser();
                break;
            }

            case XML_FILE: {
                //dataSourceReader = new MarksWithModelsXmlDomParser();
                dataSourceReader = new MarksWithModelXmlStaxParser();
                //    dataSourceReader = new MarksWithModelsXmlSaxParser();
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }

    public Exception getParseException() {
        return parseException;
    }
}
