package ru.rsreu.ars.utils;

import com.google.common.io.Files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.io.File;


public class FileReader {
    public static String getStringFromFiles(List<File> files){
        StringBuilder sb = new StringBuilder();
        FileReader reader = null;
        for(File file : files){
            if(file.isDirectory()){
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
}
