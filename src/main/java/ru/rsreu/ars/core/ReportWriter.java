package ru.rsreu.ars.core;

import ru.rsreu.ars.core.beans.Report;

import java.io.File;

public interface ReportWriter {
    void writeReportFile(File archive, File templateForReport, File configuration, String outputFileName, Report report);
}
