package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.CommonExceptionMeta.JDBC_SQL_ERROR;

public class SqlError extends AutoServiceUncheckedException {

    public SqlError(Exception cause) {
        this(JDBC_SQL_ERROR.getCode(), JDBC_SQL_ERROR.getDescription(), cause);
    }

    private SqlError(int code, String message, Exception cause) {
        super(code, message, cause);
    }
}
