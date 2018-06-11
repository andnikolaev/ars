package ru.rsreu.ars.core;

import com.tutego.jrtf.Rtf;
import com.tutego.jrtf.RtfInfo;
import ru.rsreu.ars.core.beans.Report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/***
 * Класс для записи отчета в rtf файл
 */
public class RtfReportWriter implements ReportWriter {
    /***
     * Запись отчета в rtf Файл
     * @param templateForReport Шаблон отчета
     * @param outputFileName Имя сохраненного отчета
     * @param report Информация для отчета
     */
    @Override
    public void writeReportFile(File archive, File templateForReport, File configuration, String outputFileName, Report report) {
        try (FileInputStream fio = new FileInputStream(templateForReport); FileOutputStream fos = new FileOutputStream(outputFileName)) {
            Rtf.template(fio).info(RtfInfo.emptyHashId()).injectAllNonReserved(report.getUserInformationMap())
                    .inject("Code", report.getCheckstyleResult() + report.getListing()).out(fos);
            fio.close();
            fos.close();
            String hash = "";
            hash = Security.getMD5Checksum(archive.getAbsolutePath(), configuration.getAbsolutePath(), templateForReport.getAbsolutePath(), outputFileName);
            String search = "{\\id }";
            String replace = "{\\id " + Rtf.asRtf(hash) + "}";
            Charset charset = Charset.forName("Windows-1252");
            Path path = Paths.get(outputFileName);
            Files.write(path,
                    new String(Files.readAllBytes(path), charset).replace(search, replace)
                            .getBytes(charset));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
