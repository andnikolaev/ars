package ru.rsreu.ars.controllers;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserConfiguration {
    public static FileChooser setProjectFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Choose archive with java project");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All zips", "*.zip")
        );
        return fileChooser;
    }
    public static FileChooser setTemplateFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Choose template for report");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All rtf", "*.rtf")
        );
        return fileChooser;
    }
    public static FileChooser setCheckstyleConfigurationFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Choose checkstyle configuration");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All xml", "*.xml")
        );
        return fileChooser;
    }
}
