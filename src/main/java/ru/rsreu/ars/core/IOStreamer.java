package ru.rsreu.ars.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.io.File;

import com.tutego.jrtf.Rtf;

public class IOStreamer {

    public static String getDataForTemplate(File file, List<ZipEntry> zipEntry) {
        ZipFile zf = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            zf = new ZipFile(file);
            for (ZipEntry entry : zipEntry) {
                System.out.println("Read " + entry.getName());
                br = new BufferedReader(new InputStreamReader(zf.getInputStream(entry)));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                    sb.append("\n");
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (zf != null) {
                    zf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void writeRtfFile(String template, String ouputFileName, Report report) {
        FileInputStream fio = null;
        FileOutputStream fos = null;
        try {
            fio = new FileInputStream(template);
            fos = new FileOutputStream(ouputFileName);
            Rtf.template(fio).inject("Num", report.getNumber()).inject("Student", String.format("%s\n%s", report.getGroup(), report.getOwner()))
                    .inject("Code", report.getListing()).out(fos);
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

    public static List<ZipEntry> getClassesEntry(File file) {
        List<ZipEntry> zipEntries = new ArrayList<>();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                if (entry.getName().contains("/src/ru/rsreu/nikolaev")) {
                    zipEntries.add(entry);
                    name = entry.getName();
                    System.out.printf("Название: %s \n", name);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return zipEntries;
    }

}
