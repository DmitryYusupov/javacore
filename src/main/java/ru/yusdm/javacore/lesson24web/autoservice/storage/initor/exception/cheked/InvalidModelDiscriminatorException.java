package ru.yusdm.javacore.lesson24web.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.AutoServiceCheckedException;

public class InvalidModelDiscriminatorException extends AutoServiceCheckedException {

    public InvalidModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
