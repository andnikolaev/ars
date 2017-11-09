package ru.rsreu.ars.core;

import java.io.File;
import java.util.List;
import java.util.zip.ZipEntry;

public class MainModel {
    private File file;

    public void generateReport(Report report){
        List<ZipEntry> fileEntries = IOStreamer.getClassesEntry(file);
        report.setListing(IOStreamer.getDataForTemplate(file, fileEntries));
        IOStreamer.writeRtfFile("template1.rtf", "report.rtf", report);
    }

    public void setFile(File file){
        this.file = file;
    }
    public File getFile(){
        return file;
    }
}
