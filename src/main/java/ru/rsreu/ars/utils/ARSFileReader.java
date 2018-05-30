package ru.rsreu.ars.utils;

import com.google.common.io.Files;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ARSFileReader {
    public static String getStringFromFiles(List<File> files) {
        StringBuilder sb = new StringBuilder();
        FileReader reader = null;
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            try {
                sb.append(Files.readLines(file, Charset.defaultCharset()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }

    public static String getStringFromFiles2(List<File> files) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            for (File file : files) {
                if (!file.isDirectory() && !file.getName().contains(".class")) {
                    System.out.println("Read " + file.getName());
                    br = new BufferedReader(new FileReader(file));
                    String line;
                    sb.append(file.getName());
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        sb.append(line);
                        sb.append("\n");
                    }
                    br.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
