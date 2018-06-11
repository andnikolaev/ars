package ru.rsreu.ars.core;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.tutego.jrtf.Rtf;
import ru.rsreu.ars.core.beans.Report;
import ru.rsreu.ars.core.beans.CheckResult;
import ru.rsreu.ars.core.checks.CodeChecker;
import ru.rsreu.ars.core.annotations.RunCheck;
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

/***
 * Класс, где содержится основная бизнес-логика сситемы
 */
public class ARSModel {
    //Архив с проектом
    private File file;
    //Шаблон
    private File templateForReport;
    //Конфигурация checkstyle
    private File configuration;
    //Файлы для листинга
    private List<File> filesForListing;

    /***
     * Формирование отчета
     * @param report Информация для отчета
     * @param reportWriter Класс для сохранения отчета
     */
    public void generateReport(Report report, ReportWriter reportWriter) {
        unzipFile(file);
        report.setListing(ARSFileReader.getStringFromFiles2(filesForListing));

        reportWriter.writeReportFile(file, templateForReport, configuration,getUnzipDirectory(file.getName()) + ".rtf",report);
        deleteDirectory(new File(getUnzipDirectory(file.getName())));
    }

    /***
     * Запуск проверок исходного кода
     * @return Результат проверок
     * @throws FileNotFoundException Ошибка. Файл не найден.
     * @throws CheckstyleException Ошибка checkstyle.
     */
    public String runChecks() throws FileNotFoundException, CheckstyleException {
        Class c = CodeChecker.class;
        Method[] methods = c.getMethods();
        StringBuilder stringBuilder = new StringBuilder();
        for(Method mt : methods) {
            if (mt.isAnnotationPresent(RunCheck.class)) {
                // Invoke method with appropriate arguments
                try {
                    Object obj = mt.invoke(c.newInstance(),file, configuration);
                    CheckResult checkResult = (CheckResult) obj;
                    stringBuilder.append(checkResult.getResult());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    /***
     * Удаление директории
     * @param directory Удаляемая лиректория
     * @return Результат удаления
     */
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

    /***
     * Получить все метки из шаблона
     * @return Карта меток
     * @throws IOException Ошибка чтения файла шаблона
     */
    public Map<String, String> getAllIdentifiersFromTemplate() throws IOException {
        FileInputStream fio = new FileInputStream(templateForReport);
        Map<String, String> map = Rtf.template(fio).findAllIdentificators();
        fio.close();
        return map;
    }

    /***
     * Заполнить метки текстом
     * @param identifiers Метки
     * @return Карта меток
     */
    public Map<String, String> fillIdentifiersWithText(Set<String> identifiers) {
        Map<String, String> identifiersWithText = new HashMap<>();
        for (String identifierKey : identifiers) {
            identifiersWithText.put(identifierKey, getIdentifierText(identifierKey));
        }

        return identifiersWithText;
    }

    /***
     * Получение текста из метки
     * @param identifier Метка
     * @return Текст из метки
     */
    private String getIdentifierText(String identifier) {
        return Resourcer.getString(identifier);
    }

    /***
     * Разархивация файла
     * @param file Архив
     * @return Директория
     */
    public String unzipFile(File file) {
        String unzipDirectory = getUnzipDirectory(file.getName());
        ZIPHandler.unZipIt(file.getAbsolutePath(), unzipDirectory);
        return unzipDirectory;
    }

    /***
     * Получение директории для разархивации
     * @param fileName архив
     * @return Имя директории
     */
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
