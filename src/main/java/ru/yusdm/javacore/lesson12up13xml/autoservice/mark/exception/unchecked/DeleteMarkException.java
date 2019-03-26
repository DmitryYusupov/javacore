package ru.yusdm.javacore.lesson12up13xml.autoservice.mark.exception.unchecked;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.exception.MarkExceptionMeta;

public class DeleteMarkException extends AutoServiceUncheckedException {

    public DeleteMarkException(int code, String message) {
        super(code, message);
    }

    public DeleteMarkException(MarkExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
