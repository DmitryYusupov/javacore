package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.exception.unchecked;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;

import static ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.exception.UserExceptionMeta.JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR;

public class UnknownUserClientTypeException extends AutoServiceUncheckedException {
    public UnknownUserClientTypeException(String unknownClientType) {
        super(JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR.getCode(), JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR.getDescriptionAsFormatStr(unknownClientType));
    }
}
