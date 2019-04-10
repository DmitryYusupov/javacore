package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception;

public enum CommonExceptionMeta {
    SQL_ERROR(1, "Sql error"),
    CONNECTION_ACHIVE_ERROR(10, "Cant get connection"),
    KEY_GENERATION_ERROR(20, "Key for filed '%' didn't generated");

    private int code;
    private String description;

    CommonExceptionMeta(int code, String description) {
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
