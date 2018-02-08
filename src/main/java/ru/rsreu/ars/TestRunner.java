package ru.rsreu.ars;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Optional;


public class TestRunner extends Application {
    private Text actionStatus;
    private static final String titleTxt = "JavaFX Dialogs Example";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Sample");

        CheckBoxTreeItem<String> rootItem =
                new CheckBoxTreeItem<String>("View Source Files");
        rootItem.setExpanded(true);

        final TreeView tree = new TreeView(rootItem);
        tree.setEditable(true);

        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        for (int i = 0; i < 8; i++) {
            final CheckBoxTreeItem<String> checkBoxTreeItem =
                    new CheckBoxTreeItem<String>("Sample" + (i+1));
            rootItem.getChildren().add(checkBoxTreeItem);
        }

        tree.setRoot(rootItem);
        tree.setShowRoot(true);

        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        displayDialog();
        primaryStage.show();
    }

    private void displayDialog() {


    }

    private class PhoneBook {

        private String name;
        private String phone;

        PhoneBook(String s1, String s2) {

            name = s1;
            phone = s2;
        }

        @Override
        public String toString() {

            return (name + ", " + phone);
        }
    }
}