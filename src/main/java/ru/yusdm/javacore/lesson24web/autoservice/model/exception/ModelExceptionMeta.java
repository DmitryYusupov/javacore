package ru.yusdm.javacore.lesson24web.autoservice.model.exception;

public enum ModelExceptionMeta {

    DELETE_MODEL_CONSTRAINT_ERROR(1, "Error while delete model. There is constraint violation!"),
    JDBC_UNKNOWN_MODEL_DISCRIMINATOR_ERROR(20, "Unknown discriminator '%s'");

    private int code;
    private String description;

    ModelExceptionMeta(int code, String description) {
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
