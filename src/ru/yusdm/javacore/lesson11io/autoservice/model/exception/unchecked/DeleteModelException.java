package ru.yusdm.javacore.lesson11io.autoservice.model.exception.unchecked;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson11io.autoservice.model.exception.ModelExceptionMeta;

public class DeleteModelException extends AutoServiceUncheckedException {

    public DeleteModelException(String message, int code) {
        super(message, code);
    }

    public DeleteModelException(ModelExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
