package ru.yusdm.javacore.lesson14serialization.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
