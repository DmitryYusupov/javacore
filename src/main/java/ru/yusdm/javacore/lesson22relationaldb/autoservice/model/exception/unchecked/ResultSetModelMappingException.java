package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.exception.unchecked;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;

public class ResultSetModelMappingException extends AutoServiceUncheckedException {
    public ResultSetModelMappingException(int code, String message) {
        super(code, message);
    }

    public ResultSetModelMappingException(int code, String message, Exception cause) {
        super(code, message, cause);
    }
}
