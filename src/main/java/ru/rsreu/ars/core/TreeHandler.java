package ru.rsreu.ars.core;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class TreeHandler {

    public static void createTree(File file, CheckBoxTreeItem<String> parent){
        if (file.isDirectory()) {
            CheckBoxTreeItem<String> treeItem = new CheckBoxTreeItem<>(file.getName());
            parent.getChildren().add(treeItem);
            for (File f : file.listFiles()) {
                createTree(f, treeItem);
            }
        } else {
            parent.getChildren().add(new CheckBoxTreeItem<>(file.getName()));
        }
    }

    public static List<String> getAllSelected(TreeView tree) {
        List<String> treeItems = new LinkedList<>();
        System.out.println(tree.getSelectionModel().getSelectedItems().toArray().toString());
        return  null;
    }
}
