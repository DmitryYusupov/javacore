package ru.yusdm.javacore.lesson5oopinterface.atmwithinterface.instanceofvsgetclass;

/**
 * Created by Admin on 2/22/2019.
 */
public class Report {

    private String value;

    public Report(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Report{" +
                "value='" + value + '\'' +
                '}';
    }
}
