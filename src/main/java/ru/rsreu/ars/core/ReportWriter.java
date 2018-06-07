package ru.rsreu.ars.core;

import ru.rsreu.ars.core.beans.Report;

import java.io.File;

public interface ReportWriter {
    void writeReportFile(File templateForReport, String outputFileName, Report report, String hash);
}
