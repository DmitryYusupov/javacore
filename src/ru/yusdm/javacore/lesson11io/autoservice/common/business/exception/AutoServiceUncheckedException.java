package ru.yusdm.javacore.lesson11io.autoservice.common.business.exception;

public abstract class AutoServiceUncheckedException extends RuntimeException {
    protected int code;

    public AutoServiceUncheckedException(String message, int code) {
        super(message);
        this.code = code;
    }
}
