package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.CommonExceptionMeta.KEY_GENERATION_ERROR;

public class KeyGenerationError extends AutoServiceUncheckedException{

    public KeyGenerationError(String generatedKey) {
        super(KEY_GENERATION_ERROR.getCode(), KEY_GENERATION_ERROR.getDescriptionAsFormatStr(generatedKey));
    }

}
