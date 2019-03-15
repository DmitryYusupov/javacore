package ru.yusdm.javacore.lesson11io.autoservice.mark.exception.unchecked;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson11io.autoservice.mark.exception.MarkExceptionMeta;

public class DeleteMarkException extends AutoServiceUncheckedException {

    public DeleteMarkException(int code, String message) {
        super(code, message);
    }

    public DeleteMarkException(MarkExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
