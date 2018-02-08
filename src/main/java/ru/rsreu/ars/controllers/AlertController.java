package ru.rsreu.ars.controllers;

import javafx.scene.control.Alert;

public class AlertController {
    public static void showEmptyTemplateFileAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Choose the file!");
        alert.showAndWait();
    }


}
