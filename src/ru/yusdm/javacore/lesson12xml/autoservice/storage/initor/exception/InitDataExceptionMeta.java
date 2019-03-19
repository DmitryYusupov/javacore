package ru.yusdm.javacore.lesson12xml.autoservice.storage.initor.exception;

public enum InitDataExceptionMeta {

    PARSE_MODEL_DISCRIMINATOR_ERROR(1, "Unknown model discriminator '%s'.");

    private int code;
    private String description;

    InitDataExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAsFormatStr(Object... args) {
        return String.format(description, args);
    }
}
