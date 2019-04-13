package ru.yusdm.javacore.lesson24web.autoservice.model.exception.unchecked;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.AutoServiceUncheckedException;

import static ru.yusdm.javacore.lesson24web.autoservice.model.exception.ModelExceptionMeta.JDBC_UNKNOWN_MODEL_DISCRIMINATOR_ERROR;

public class UnknownModelDiscriminatorException extends AutoServiceUncheckedException {

    public UnknownModelDiscriminatorException() {
        this(JDBC_UNKNOWN_MODEL_DISCRIMINATOR_ERROR.getCode(), JDBC_UNKNOWN_MODEL_DISCRIMINATOR_ERROR.getDescription());
    }
    public UnknownModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
