package ru.yusdm.javacore.lesson11ionio.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson11ionio.autoservice.common.business.exception.AutoServiceCheckedException;

public class InvalidModelDiscriminatorException extends AutoServiceCheckedException {

    public InvalidModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
