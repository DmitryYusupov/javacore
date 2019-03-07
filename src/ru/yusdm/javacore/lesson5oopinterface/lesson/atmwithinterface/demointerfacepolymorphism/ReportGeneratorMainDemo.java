package ru.yusdm.javacore.lesson5oopinterface.lesson.atmwithinterface.demointerfacepolymorphism;

/**
 * Created by Admin on 2/22/2019.
 */
public class ReportGeneratorMainDemo {

    public static void main(String[] args) {
        ReportGenerator[] reportGenerators
                = new ReportGenerator[]{
                new PdfReportGenerator(),
                new ExcelReportGenerator()
        };

        for (ReportGenerator reportGenerator :reportGenerators){

            System.out.println(reportGenerator.generateReport());

        }
    }


}
