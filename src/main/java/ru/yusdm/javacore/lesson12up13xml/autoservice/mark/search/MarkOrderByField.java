package ru.yusdm.javacore.lesson12up13xml.autoservice.mark.search;

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
