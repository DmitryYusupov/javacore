package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
