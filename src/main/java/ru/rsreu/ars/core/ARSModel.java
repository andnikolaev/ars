package ru.rsreu.ars.core;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.tutego.jrtf.Rtf;
import ru.rsreu.ars.core.beans.Report;
import ru.rsreu.ars.core.beans.CheckResult;
import ru.rsreu.ars.core.checks.CodeChecker;
import ru.rsreu.ars.core.checks.RunCheck;
import ru.rsreu.ars.utils.ARSFileReader;
import ru.rsreu.ars.utils.Resourcer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class ARSModel {
    private File file;
    private File templateForReport;
    private File configuration;
    private List<File> filesForListing;


    public void generateReport(Report report, ReportWriter reportWriter) {
//        List<ZipEntry> fileEntries = ZIPHandler.getClassesEntry(file);
//        report.setListing(ZIPHandler.getDataForTemplate(file, fileEntries));
        unzipFile(file);
        report.setListing(ARSFileReader.getStringFromFiles2(filesForListing));
        String hash = null;
        try{
           hash = Security.getMD5Checksum(file.getAbsolutePath(), configuration.getAbsolutePath());
        } catch (Exception e){
            e.printStackTrace();
        }
        reportWriter.writeReportFile(templateForReport,getUnzipDirectory(file.getName()) + ".rtf",report, hash);
        deleteDirectory(new File(getUnzipDirectory(file.getName())));
    }


    public String runChecks() throws FileNotFoundException, CheckstyleException {
        Class c = CodeChecker.class;
        Method[] methods = c.getMethods();
        StringBuilder stringBuilder = new StringBuilder();
        for(Method mt : methods) {
            if (mt.isAnnotationPresent(RunCheck.class)) {
                // Invoke method with appropriate arguments
                try {
                    Object obj = mt.invoke(c,file, configuration);
                    CheckResult checkResult = (CheckResult) obj;
                    stringBuilder.append(checkResult.getResult());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
//        StringBuilder checkstyleResult = new StringBuilder();
//
//        List<ZipEntry> fileEntries = ZIPHandler.getClassesEntry(file);
//        for (ZipEntry entry : fileEntries) {
//            if (entry.getName().contains(".java")) {
//                String sourceFilePath = getUnzipDirectory(file.getName()) + File.separator + entry.getName();
//                checkstyleResult.append(Checkstyle.start(sourceFilePath, configuration.getAbsolutePath()));
//            }
//        }
//        return checkstyleResult.toString();
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

    public Map<String, String> getAllIdentifiersFromTemplate() throws IOException {
        FileInputStream fio = new FileInputStream(templateForReport);
        Map<String, String> map = Rtf.template(fio).findAllIdentificators();
        fio.close();
        return map;
    }

    public Map<String, String> fillIdentifiersWithText(Set<String> identifiers) {
        Map<String, String> identifiersWithText = new HashMap<>();
        for (String identifierKey : identifiers) {
            identifiersWithText.put(identifierKey, getIdentifierText(identifierKey));
        }

        return identifiersWithText;
    }

    private String getIdentifierText(String identifier) {
        return Resourcer.getString(identifier);
    }

    public String unzipFile(File file) {
        String unzipDirectory = getUnzipDirectory(file.getName());
        ZIPHandler.unZipIt(file.getAbsolutePath(), unzipDirectory);
        return unzipDirectory;
    }

    public static String getUnzipDirectory(String fileName) {
        String[] unzip = fileName.split(Pattern.quote(File.separator));
        return unzip[unzip.length - 1].replace(".zip", "");
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public File getTemplateForReport() {
        return templateForReport;
    }

    public void setTemplateForReport(File templateForReport) {
        this.templateForReport = templateForReport;
    }

    public File getConfiguration() {
        return configuration;
    }

    public void setConfiguration(File configuration) {
        this.configuration = configuration;
    }

    public List<File> getFilesForListing() {
        return filesForListing;
    }

    public void setFilesForListing(List<File> filesForListing) {
        this.filesForListing = filesForListing;
    }

}
