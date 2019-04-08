package ru.yusdm.javacore.lesson21regularexpressions.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
