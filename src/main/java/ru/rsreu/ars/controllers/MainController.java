package ru.rsreu.ars.controllers;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javafx.fxml.FXML;
import ru.rsreu.ars.core.Report;
import ru.rsreu.ars.core.MainModel;
import ru.rsreu.ars.core.TreeHandler;

/**
 * genarated by APX file generation template
 * File name: MainController.java
 */
public class MainController {

    @FXML
    Label fileNameLabel;
    @FXML
    Label labelTemplate;
    @FXML
    Label labelConfiguration;
    @FXML
    TextField labNumber;
    @FXML
    TextField groupNumber;
    @FXML
    TextField studentName;
    @FXML
    TextArea checkstyleMessage;
    @FXML
    TreeView filesTreeView;


    private MainModel model = new MainModel();
    private FileChooser fileChooser = new FileChooser();
    private TreeHandler treeHandler;

    @FXML
    private void getFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        fileChooser = FileChooserConfiguration.setProjectFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            model.setFile(file);
            fileNameLabel.setText(file.getAbsolutePath());

            //Checkstyle
            try {
                checkstyleMessage.setText(model.checkstyle());
            } catch (FileNotFoundException | CheckstyleException e) {
                checkstyleMessage.setText(e.getMessage() + "\n" + e.getCause().getMessage());
            }

            //Generate tree
            String unzipDirectory = model.unzipFile(file);
            treeHandler = new TreeHandler(filesTreeView, unzipDirectory);
            treeHandler.createAllTree();
        }
    }

    @FXML
    private void chooseConfiguration(){

    }

    @FXML
    private void chooseTemplate(){

    }

    @FXML
    private void processFile() {
        treeHandler.getAllSelected(filesTreeView);
        if (model.getFile() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Choose the file!");
            alert.showAndWait();
        } else if (studentName.getText().trim().isEmpty() || labNumber.getText().trim().isEmpty() || groupNumber.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Fill all inputs!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           try {
               model.generateReport(new Report(studentName.getText(), groupNumber.getText(), labNumber.getText(), checkstyleMessage.getText()));
               alert.setTitle("Information Dialog");
               alert.setHeaderText(null);
               alert.setContentText("I have a great message for you!");
               alert.showAndWait();
           } catch (Exception e){
               alert.setTitle("Information Dialog");
               alert.setHeaderText(null);
               alert.setContentText("Please close file with report!");
               alert.showAndWait();
           }
        }
    }
}