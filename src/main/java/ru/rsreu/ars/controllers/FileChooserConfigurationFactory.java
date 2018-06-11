package ru.rsreu.ars.controllers;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserConfigurationFactory {
    public static FileChooser makeProjectFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите архив с проектом");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All zips", "*.zip")
        );
        return fileChooser;
    }
    public static FileChooser makeTemplateFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите шаблон для отчета");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All rtf", "*.rtf")
        );
        return fileChooser;
    }
    public static FileChooser makeCheckstyleConfigurationFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите конфигурацию checkstyle");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All xml", "*.xml")
        );
        return fileChooser;
    }
}
