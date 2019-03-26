package ru.yusdm.javacore.lesson12up13xml.autoservice.reporting;

import java.io.File;

public interface ReportComponent {
    File generateReport() throws Exception;
}
