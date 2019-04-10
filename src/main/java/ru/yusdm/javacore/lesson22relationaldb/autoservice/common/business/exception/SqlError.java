package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.CommonExceptionMeta.SQL_ERROR;

public class SqlError extends AutoServiceUncheckedException {

    public SqlError(Exception cause) {
        this(SQL_ERROR.getCode(), SQL_ERROR.getDescription(), cause);
    }

    private SqlError(int code, String message, Exception cause) {
        super(code, message, cause);
    }
}
