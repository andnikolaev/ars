package ru.rsreu.ars.controllers;

import javafx.scene.control.Alert;

public class AlertController {
    public static void showEmptyTemplateFileAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Файл с шаблоном не содержит меток для вставки информации!");
        alert.showAndWait();
    }

    public static void showNonSelectFileForListingAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Выберите файлы для листинга!");
        alert.showAndWait();
    }

    public static void showEmptyTemplateLinkAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Выберите шаблон для отчета!");
        alert.showAndWait();
    }

    public static void showEmptySourceFileLinkAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Выберти архив с проектом!");
        alert.showAndWait();
    }

    public static void showEmptyCheckstyleConfigurationLinkAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Выберите файл с конфигурацией checkstyle!");
        alert.showAndWait();
    }


}
