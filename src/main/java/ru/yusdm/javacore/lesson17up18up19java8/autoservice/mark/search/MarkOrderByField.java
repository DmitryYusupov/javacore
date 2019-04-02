package ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.search;

public enum MarkOrderByField {
    COUNTRY("markcountry"),NAME("markname");

    MarkOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
