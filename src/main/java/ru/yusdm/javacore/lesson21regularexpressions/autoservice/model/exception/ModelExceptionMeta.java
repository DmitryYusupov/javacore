package ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.exception;

public enum ModelExceptionMeta {

    DELETE_MODEL_CONSTRAINT_ERROR(1, "Error while delete model. There is constraint violation!");

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

}
