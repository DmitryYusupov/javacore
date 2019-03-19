package ru.yusdm.javacore.lesson11ionio.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
