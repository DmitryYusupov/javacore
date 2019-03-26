package ru.yusdm.javacore.lesson15concurrency.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
