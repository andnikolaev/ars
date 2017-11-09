package ru.rsreu.ars.controllers;

import javafx.event.ActionEvent;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javafx.fxml.FXML;
import ru.rsreu.ars.core.Report;
import ru.rsreu.ars.core.MainModel;

/**
 * genarated by APX file generation template
 * File name: MainController.java
 */
public class MainController {

    @FXML
    Label fileNameLabel;
    @FXML
    TextField labNumber;
    @FXML
    TextField groupNumber;
    @FXML
    TextField studentName;

    private MainModel model = new MainModel();
    final private FileChooser fileChooser;

    {
        fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All zips", "*.zip")
        );
    }

    @FXML
    private void getFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        model.setFile(file);
        fileNameLabel.setText(file.getAbsolutePath());
    }

    @FXML
    private void processFile() {
        if (model.getFile() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Careful with the next step!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");
            model.generateReport(new Report(studentName.getText(),groupNumber.getText(),labNumber.getText()));
            alert.showAndWait();
        }
    }
}