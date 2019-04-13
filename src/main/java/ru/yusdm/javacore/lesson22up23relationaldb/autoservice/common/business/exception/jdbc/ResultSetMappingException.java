package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.exception.jdbc;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;

import static ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.exception.CommonExceptionMeta.JDBC_RESULT_SET_MAPPING_ERROR;

public class ResultSetMappingException extends AutoServiceUncheckedException {
    public ResultSetMappingException(String entityClassName, Exception e) {
        super(JDBC_RESULT_SET_MAPPING_ERROR.getCode(), JDBC_RESULT_SET_MAPPING_ERROR.getDescriptionAsFormatStr(entityClassName), e);
    }
}
