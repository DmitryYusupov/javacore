package ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.initor;


import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.initor.exception.cheked.MarkModelParseXmlFileException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.initor.exception.InitDataExceptionMeta.PARSE_MARK_MODEL_ERROR;

public class StorageInitializer {

    private final MarkService markService;

    public StorageInitializer(MarkService markService) {
        this.markService = markService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    public void initStorageWithMarksAndModels(List<File> files, DataSourceType dataSourceType) throws Exception {
        List<MarkModelFileParser> markModelFileParsers = prepareAsyncParsers(files, dataSourceType);
        List<Mark> marksToPersist = asyncParseFilesAndWaitForResult(markModelFileParsers);

        if (!marksToPersist.isEmpty()) {
            for (Mark mark : marksToPersist) {
                markService.insert(mark);
            }
        }
    }

    private List<MarkModelFileParser> prepareAsyncParsers(List<File> files, DataSourceType dataSourceType) {
        List<MarkModelFileParser> markModelFileParsers = new ArrayList<>();
        for (File file : files) {
            markModelFileParsers.add(new MarkModelFileParser(dataSourceType, file));
        }
        return markModelFileParsers;
    }

    private List<Mark> asyncParseFilesAndWaitForResult(List<MarkModelFileParser> workers) throws Exception {
        for (MarkModelFileParser worker : workers) {
            worker.asyncParseMarks();
        }

        List<Mark> marksToPersist = new ArrayList<>();
        for (MarkModelFileParser worker : workers) {
            worker.blockUntilJobIsFinished();
            if (worker.getParseException() != null) {
                throw new MarkModelParseXmlFileException(PARSE_MARK_MODEL_ERROR.getCode(), PARSE_MARK_MODEL_ERROR.getDescription(), worker.getParseException());
            }
            marksToPersist.addAll(worker.getMarks());
        }

        return marksToPersist;

    }

}
