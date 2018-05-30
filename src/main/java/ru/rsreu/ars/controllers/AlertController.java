package ru.rsreu.ars.controllers;

import javafx.scene.control.Alert;

public class AlertController {
    public static void showEmptyTemplateFileAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Template file does not contain tags!");
        alert.showAndWait();
    }

    public static void showNonSelectFileForListingAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Select files for listing!");
        alert.showAndWait();
    }

    public static void showEmptyTemplateLinkAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Choose the template file!");
        alert.showAndWait();
    }

    public static void showEmptySourceFileLinkAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Choose the archive file!");
        alert.showAndWait();
    }

    public static void showEmptyCheckstyleConfigurationLinkAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Choose the checkstyle configuration file!");
        alert.showAndWait();
    }


}
