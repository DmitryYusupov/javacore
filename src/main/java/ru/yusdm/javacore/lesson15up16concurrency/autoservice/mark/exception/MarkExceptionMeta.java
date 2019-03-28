package ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.exception;

public enum MarkExceptionMeta {
    DELETE_MARK_CONSTRAINT_ERROR(1, "Error while delete mark. There is constraint violation!");

    private int code;
    private String description;

    MarkExceptionMeta(int code, String description) {
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
