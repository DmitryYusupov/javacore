package ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.exception.AutoServiceCheckedException;

public class InvalidModelDiscriminatorException extends AutoServiceCheckedException {

    public InvalidModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
