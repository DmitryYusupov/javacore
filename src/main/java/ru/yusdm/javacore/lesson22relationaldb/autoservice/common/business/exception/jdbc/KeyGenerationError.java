package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.AutoServiceUncheckedException;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.CommonExceptionMeta.JDBC_KEY_GENERATION_ERROR;

public class KeyGenerationError extends AutoServiceUncheckedException {

    public KeyGenerationError(String generatedKey) {
        super(JDBC_KEY_GENERATION_ERROR.getCode(), JDBC_KEY_GENERATION_ERROR.getDescriptionAsFormatStr(generatedKey));
    }

}
