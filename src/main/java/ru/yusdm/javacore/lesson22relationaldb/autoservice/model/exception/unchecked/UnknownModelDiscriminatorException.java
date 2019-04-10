package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.exception.unchecked;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.model.exception.ModelExceptionMeta.UNKNOWN_MODEL_DISCRIMINATOR_ERROR;

public class UnknownModelDiscriminatorException extends AutoServiceUncheckedException {

    public UnknownModelDiscriminatorException() {
        this(UNKNOWN_MODEL_DISCRIMINATOR_ERROR.getCode(), UNKNOWN_MODEL_DISCRIMINATOR_ERROR.getDescription());
    }
    public UnknownModelDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
