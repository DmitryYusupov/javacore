package ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson12xml.autoservice.common.business.exception.AutoServiceCheckedException;

public class InvalidModelDiscriminatorException extends AutoServiceCheckedException {

    public InvalidModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
