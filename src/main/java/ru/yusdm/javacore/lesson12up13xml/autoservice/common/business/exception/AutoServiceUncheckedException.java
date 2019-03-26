package ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.exception;

public abstract class AutoServiceUncheckedException extends RuntimeException {
    protected int code;

    public AutoServiceUncheckedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public AutoServiceUncheckedException(int code, String message, Exception cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }
}
