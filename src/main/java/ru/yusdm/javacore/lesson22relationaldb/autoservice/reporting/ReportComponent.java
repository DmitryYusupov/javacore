package ru.yusdm.javacore.lesson22relationaldb.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
