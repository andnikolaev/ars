package ru.rsreu.ars.controllers;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.stage.FileChooser;

import javafx.fxml.FXML;
import ru.rsreu.ars.core.Report;
import ru.rsreu.ars.core.MainModel;
import ru.rsreu.ars.core.TreeHandler;
import ru.rsreu.ars.core.ZIPHandler;

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
    final private FileChooser fileChooser;

    {
        fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File("projects")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All zips", "*.zip")
        );
    }

    @FXML
    private void getFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            model.setFile(file);
            String unzipDirectory = model.getUnzipDirectory(file.getName());
            ZIPHandler.unZipIt(file.getAbsolutePath(), unzipDirectory);
            fileNameLabel.setText(file.getAbsolutePath());
            try {
                checkstyleMessage.setText(model.checkstyle());
            } catch (FileNotFoundException | CheckstyleException e) {
                checkstyleMessage.setText(e.getMessage() + "\n" + e.getCause().getMessage());
            }
            CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>(unzipDirectory);

            // Hides the root item of the tree view.
            filesTreeView.setShowRoot(false);

            // Creates the cell factory.
            filesTreeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

            // Get a list of files.
            File fileInputDirectoryLocation = new File(unzipDirectory);
            File fileList[] = fileInputDirectoryLocation.listFiles();

            // create tree
            for (File files : fileList) {
                TreeHandler.createTree(files, rootItem);
            }

            filesTreeView.setRoot(rootItem);

            TreeHandler.getAllSelected(filesTreeView);
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