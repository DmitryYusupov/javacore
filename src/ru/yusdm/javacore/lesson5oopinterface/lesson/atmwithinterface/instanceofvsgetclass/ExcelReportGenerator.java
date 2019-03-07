package ru.yusdm.javacore.lesson5oopinterface.lesson.atmwithinterface.instanceofvsgetclass;

/**
 * Created by Admin on 2/22/2019.
 */
public class ExcelReportGenerator
        implements ReportGenerator, ComplexReportGenerator {
    @Override
    public Report generateReport() {
        return new Report("Excel report");
    }
}
