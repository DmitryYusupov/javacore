package ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.search;

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
