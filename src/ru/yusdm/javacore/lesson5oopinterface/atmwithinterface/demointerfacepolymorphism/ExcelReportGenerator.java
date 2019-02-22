package ru.yusdm.javacore.lesson5oopinterface.atmwithinterface.demointerfacepolymorphism;

/**
 * Created by Admin on 2/22/2019.
 */
public class ExcelReportGenerator implements ReportGenerator {
    @Override
    public Report generateReport() {
        return new Report("Excel report");
    }
}
