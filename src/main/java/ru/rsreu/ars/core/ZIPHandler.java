package ru.rsreu.ars.core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZIPHandler {

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

    /**
     * Unzip it
     * @param zipFile input zip file
     */
    public static void unZipIt(String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{
            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static List<ZipEntry> getClassesEntry(File file) {
        List<ZipEntry> zipEntries = new ArrayList<>();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                if (entry.getName().endsWith(".java")) {
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
