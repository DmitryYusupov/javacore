package ru.yusdm.javacore.lesson14serialization.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson14serialization.autoservice.common.business.exception.AutoServiceCheckedException;

public class InvalidModelDiscriminatorException extends AutoServiceCheckedException {

    public InvalidModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
