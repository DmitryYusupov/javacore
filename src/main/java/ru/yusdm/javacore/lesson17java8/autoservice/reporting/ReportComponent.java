package ru.yusdm.javacore.lesson17java8.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
