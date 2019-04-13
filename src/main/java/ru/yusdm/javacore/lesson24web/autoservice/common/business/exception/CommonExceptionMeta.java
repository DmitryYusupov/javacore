package ru.yusdm.javacore.lesson24web.autoservice.common.business.exception;

public enum CommonExceptionMeta {
    JDBC_SQL_ERROR(1, "Sql error"),
    JDBC_CONNECTION_ACHIVE_ERROR(10, "Cant get connection"),
    JDBC_KEY_GENERATION_ERROR(20, "Key for filed '%' didn't generated"),
    JDBC_RESULT_SET_MAPPING_ERROR(30, "Error while map result of entity class '%'");

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
