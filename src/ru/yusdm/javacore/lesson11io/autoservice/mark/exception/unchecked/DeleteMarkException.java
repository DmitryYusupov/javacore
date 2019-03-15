package ru.yusdm.javacore.lesson11io.autoservice.mark.exception.unchecked;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson11io.autoservice.mark.exception.MarkExceptionMeta;

public class DeleteMarkException extends AutoServiceUncheckedException {

    public DeleteMarkException(String message, int code) {
        super(message, code);
    }

    public DeleteMarkException(MarkExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
