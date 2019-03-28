package ru.yusdm.javacore.lesson15up16concurrency.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
