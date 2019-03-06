package ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.search;

public enum MarkOrderByField {
    NAME("markname"), COUNTRY("markcountry");

    MarkOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
