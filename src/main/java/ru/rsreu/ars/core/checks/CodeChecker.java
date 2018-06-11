package ru.rsreu.ars.core.checks;

import com.puppycrawl.tools.checkstyle.Checkstyle;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import ru.rsreu.ars.core.ARSModel;
import ru.rsreu.ars.core.ZIPHandler;
import ru.rsreu.ars.core.annotations.RunCheck;
import ru.rsreu.ars.core.beans.CheckResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.ZipEntry;

/***
 * Класс для анализа кода
 */
public class CodeChecker {
    private static CodeChecker instance;

    /***
     * Реализация шаблона "Одиночка"
     * @return Экзампляр данного класса
     */
    public static CodeChecker getInstance() {
        if (instance == null) {
            instance = new CodeChecker();
        }
        return instance;
    }

    /***
     * Проверка кода с помощью checkstyle
     * @param file Архив с проектом
     * @param configuration Конфигурация checkstyle
     * @return Результат проверки
     * @throws FileNotFoundException Ошибка. Файл не найдет.
     * @throws CheckstyleException Ошибка checkstyle.
     */
    @RunCheck
    public CheckResult checkstyle(File file, File configuration) throws FileNotFoundException, CheckstyleException {
        StringBuilder checkstyleResult = new StringBuilder();

        List<ZipEntry> fileEntries = ZIPHandler.getClassesEntry(file);
        for (ZipEntry entry : fileEntries) {
            if (entry.getName().contains(".java")) {
                String sourceFilePath = ARSModel.getUnzipDirectory(file.getName()) + File.separator + entry.getName();
                checkstyleResult.append(Checkstyle.start(sourceFilePath, configuration.getAbsolutePath()));
            }
        }

        return new CheckResult(checkstyleResult.toString());
    }
}
