package ru.rsreu.ars.core;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.io.File;
import java.util.List;

public class TreeHandler {

    private CheckBoxTreeItem<String> rootItem;
    private TreeView filesTreeView;
    private String unzipDirectory;

    public TreeHandler(TreeView filesTreeView, String unzipDirectory){
        this.unzipDirectory = unzipDirectory;
        this.rootItem = new CheckBoxTreeItem<>(unzipDirectory);
        this.filesTreeView = filesTreeView;
    }

    public void createAllTree(){
        // Hides the root item of the tree view.
        filesTreeView.setShowRoot(false);
        filesTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Creates the cell factory.
        filesTreeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

        // Get a list of files.
        File fileInputDirectoryLocation = new File(unzipDirectory);
        File fileList[] = fileInputDirectoryLocation.listFiles();

        // create tree
        for (File files : fileList) {
            createTree(files, rootItem);
        }

        filesTreeView.setRoot(rootItem);
    }

    private void createTree(File file, CheckBoxTreeItem<String> parent){
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

    public List<String> getAllSelected(TreeView tree) {

        ObservableList treeItems = tree.getSelectionModel().getSelectedItems();
        Object object = tree.getSelectionModel().getSelectedItem();
        File file = null;
        for(Object obj : treeItems){
            file = (File)obj;
            System.out.println(file);
        }
        System.out.println("All selected" + tree.getSelectionModel().getSelectedItems().toArray().toString());
        return  null;
    }

}
