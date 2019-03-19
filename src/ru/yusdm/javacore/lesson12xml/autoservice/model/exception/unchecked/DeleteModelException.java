package ru.yusdm.javacore.lesson12xml.autoservice.model.exception.unchecked;

import ru.yusdm.javacore.lesson12xml.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson12xml.autoservice.model.exception.ModelExceptionMeta;

public class DeleteModelException extends AutoServiceUncheckedException {

    public DeleteModelException(int code, String message) {
        super(code, message);
    }

    public DeleteModelException(ModelExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}