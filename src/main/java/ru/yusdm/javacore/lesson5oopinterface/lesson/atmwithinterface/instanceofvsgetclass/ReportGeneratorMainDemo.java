package ru.yusdm.javacore.lesson5oopinterface.lesson.atmwithinterface.instanceofvsgetclass;

/**
 * Created by Admin on 2/22/2019.
 */
public class ReportGeneratorMainDemo {

    public static void main(String[] args) {
        ReportGenerator[] reportGenerators
                = new ReportGenerator[]{
                new PdfReportGenerator(),
                new ExcelReportGenerator(),
                new CsvReportGenerator()
        };

        System.out.println("-------Complex report (Instance of)---------");
        for (ReportGenerator reportGenerator :reportGenerators){
            if (reportGenerator instanceof ComplexReportGenerator) {
                System.out.println(reportGenerator.generateReport());
            }
        }
        System.out.println("-------Complex report (Get class)---------");
        for (ReportGenerator reportGenerator :reportGenerators){
            if (reportGenerator.getClass().equals(CsvReportGenerator.class)) {
                System.out.println(reportGenerator.generateReport());
            }
        }


    }


}
