package ru.yusdm.javacore.lesson11io.autoservice.common.business.exception;

public abstract class AutoServiceCheckedException extends Exception {

    protected int code;

    public AutoServiceCheckedException(String message, int code) {
        super(message);
        this.code = code;
    }
}
