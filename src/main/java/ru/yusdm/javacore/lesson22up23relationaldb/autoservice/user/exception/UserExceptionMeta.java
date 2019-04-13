package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.exception;

public enum UserExceptionMeta {
    JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR(1, "Unknown client type '%s'");

    private int code;
    private String description;

    UserExceptionMeta(int code, String description) {
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
