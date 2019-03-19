package ru.yusdm.javacore.lesson12xml.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
