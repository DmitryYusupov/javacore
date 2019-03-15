package ru.yusdm.javacore.lesson11io.autoservice.storage.initor.exception.cheked;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceCheckedException;

public class InitDataCheckedException extends AutoServiceCheckedException {

    public InitDataCheckedException(int code, String message) {
        super(code, message);
    }
}
