package ru.rsreu.ars.core;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import ru.rsreu.ars.core.beans.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeHandler {

    private CheckBoxTreeItem<MyFile> rootItem;
    private TreeView filesTreeView;
    private String unzipDirectory;
    private Set<TreeItem<MyFile>> selected = new HashSet<>();

    public TreeHandler(TreeView filesTreeView, String unzipDirectory) {
        this.unzipDirectory = unzipDirectory;
        this.rootItem = new CheckBoxTreeItem(unzipDirectory);
        this.filesTreeView = filesTreeView;
    }

    public void createAllTree() {
        // Hides the root item of the tree view.
        filesTreeView.setShowRoot(false);
        filesTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Creates the cell factory.
        filesTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        // Get a list of files.
        File fileInputDirectoryLocation = new File(unzipDirectory);
        File fileList[] = fileInputDirectoryLocation.listFiles();

        // create tree
        for (File file : fileList) {

            createTree(file, rootItem);
        }
// listen for selection change
        rootItem.addEventHandler(CheckBoxTreeItem.checkBoxSelectionChangedEvent(), (CheckBoxTreeItem.TreeModificationEvent<MyFile> evt) -> {
            CheckBoxTreeItem<MyFile> item = evt.getTreeItem();

            if (evt.wasIndeterminateChanged()) {
                if (item.isIndeterminate()) {
                    selected.remove(item);
                } else if (item.isSelected()) {
                    selected.add(item);
                }
            } else if (evt.wasSelectionChanged()) {
                if (item.isSelected()) {
                    selected.add(item);
                } else {
                    selected.remove(item);
                }
            }
        });
        filesTreeView.setRoot(rootItem);

    }

    private void createTree(File file, CheckBoxTreeItem<MyFile> parent) {
        if (file.getName().charAt(0) != '.' && file.getName().charAt(0) != '_') {
            System.out.println(file.getName());
            MyFile tempFile = new MyFile(file);
            if (file.isDirectory()) {

                CheckBoxTreeItem<MyFile> treeItem = new CheckBoxTreeItem(tempFile);
                parent.getChildren().add(treeItem);
                for (File f : file.listFiles()) {
                    createTree(f, treeItem);
                }
            } else {
                parent.getChildren().add(new CheckBoxTreeItem(tempFile));
            }
        }
    }

    public List<File> getAllSelected(TreeView tree) {
        List<File> selectedFiles = new ArrayList<>();
        for (TreeItem<MyFile> myFileTreeItem : selected) {
            File file = null;
            try {
                file = myFileTreeItem.getValue().getFile();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

            if (file != null && !file.isDirectory()) {
                selectedFiles.add(myFileTreeItem.getValue().getFile());
            }

        }
        return selectedFiles;
    }

}
