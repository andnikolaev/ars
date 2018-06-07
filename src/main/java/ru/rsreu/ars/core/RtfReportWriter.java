package ru.rsreu.ars.core;

import com.tutego.jrtf.Rtf;
import com.tutego.jrtf.RtfInfo;
import ru.rsreu.ars.core.beans.Report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RtfReportWriter implements ReportWriter {
    @Override
    public void writeReportFile(File templateForReport, String outputFileName, Report report, String hash) {
        try (FileInputStream fio = new FileInputStream(templateForReport); FileOutputStream fos = new FileOutputStream(outputFileName)) {
            Rtf.template(fio).info(RtfInfo.hash(hash)).injectAllNonReserved(report.getUserInformationMap())
                    .inject("Code", report.getCheckstyleResult() + report.getListing()).out(fos);
            fio.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
