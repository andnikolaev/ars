package ru.rsreu.ars.core;

import com.puppycrawl.tools.checkstyle.Checkstyle;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.tutego.jrtf.Rtf;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

public class MainModel {
    private File file;

    public void generateReport(Report report) {
        List<ZipEntry> fileEntries = ZIPHandler.getClassesEntry(file);
        report.setListing(ZIPHandler.getDataForTemplate(file, fileEntries));
        writeRtfFile("template1.rtf", getUnzipDirectory(file.getName()) + ".rtf", report);
        deleteDirectory(new File(getUnzipDirectory(file.getName())));
    }


    public String checkstyle() throws FileNotFoundException, CheckstyleException {
        StringBuilder checkstyleResult = new StringBuilder();
        String unzipDirectory = getUnzipDirectory(file.getName());
        ZIPHandler.unZipIt(file.getAbsolutePath(), unzipDirectory);
        List<ZipEntry> fileEntries = ZIPHandler.getClassesEntry(file);
        for (ZipEntry entry : fileEntries) {
            if (entry.getName().contains(".java")) {
                String sourceFilePath = unzipDirectory + "\\" + entry.getName();
                checkstyleResult.append(Checkstyle.start(sourceFilePath, "PrutzkowConfiguration.xml"));
            }
        }
        return checkstyleResult.toString();
    }

    private void writeRtfFile(String template, String ouputFileName, Report report) {
        FileInputStream fio = null;
        FileOutputStream fos = null;
        try {
            fio = new FileInputStream(template);
            fos = new FileOutputStream(ouputFileName);
            Rtf.template(fio).inject("Num", report.getNumber()).inject("Student", String.format("%s\n%s", report.getGroup(), report.getOwner()))
                    .inject("Code", report.getCheckstyleResult() + report.getListing()).out(fos);
            fio.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fio != null) {
                    fio.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        deleteDirectory(file1);
                    } else {
                        file1.delete();
                    }
                }
            }
        }
        return (directory.delete());
    }

    private String getUnzipDirectory(String fileName) {
        String[] unzip = fileName.split(Pattern.quote("\\"));
        return unzip[unzip.length - 1].replace(".zip", "");
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
