package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.CommonExceptionMeta.CONNECTION_ACHIVE_ERROR;

public class ConnectionAchiveError extends AutoServiceUncheckedException {

    public ConnectionAchiveError(Exception cause) {
        super(CONNECTION_ACHIVE_ERROR.getCode(), CONNECTION_ACHIVE_ERROR.getDescription(), cause);
    }
}
