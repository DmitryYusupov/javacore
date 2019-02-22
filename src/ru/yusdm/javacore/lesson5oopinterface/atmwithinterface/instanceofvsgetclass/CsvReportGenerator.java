package ru.yusdm.javacore.lesson5oopinterface.atmwithinterface.instanceofvsgetclass;

/**
 * Created by Admin on 2/22/2019.
 */
public class CsvReportGenerator
        implements ReportGenerator, PlainTextReportGenerator {
    @Override
    public Report generateReport() {
        return new Report("Csv report");
    }
}
