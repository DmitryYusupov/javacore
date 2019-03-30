package ru.yusdm.javacore.lesson17java8.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson17java8.autoservice.common.business.exception.AutoServiceCheckedException;

public class MarkModelParseXmlFileException extends AutoServiceCheckedException {

    public MarkModelParseXmlFileException(int code, String message, Exception cause) {
        super(code, message);
        initCause(cause);
    }
}
