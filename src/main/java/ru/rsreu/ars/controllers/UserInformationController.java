package ru.rsreu.ars.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ru.rsreu.ars.TestRunner;
import ru.rsreu.ars.core.beans.UserInformation;

import java.util.*;

public class UserInformationController {
    private Map<String, String> templateIdentifiersWithType;
    private Map<String, String> templateIdentifiersWithText;

    public UserInformationController(Map<String, String> templateIdentifiersWithType, Map<String, String> templateIdentifiersWithText) {
        this.templateIdentifiersWithType = templateIdentifiersWithType;
        this.templateIdentifiersWithText = templateIdentifiersWithText;
    }

    public void show() {

        // Custom dialog
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Test");
        dialog.setHeaderText("This is a dialog. Enter info and \n" +
                "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);


        List<UserInformation> userInformationsList = new ArrayList<>();
        for (Map.Entry<String, String> entry : templateIdentifiersWithType.entrySet())
        {
            Label label = new Label(templateIdentifiersWithText.get(entry.getKey()));
            TextField textField = new TextField();
            UserInformation userInformation = new UserInformation(entry.getKey(),label,textField);
            userInformationsList.add(userInformation);
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        // Create layout and add to dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        int i = 1;
        for(UserInformation userInformation : userInformationsList){
            grid.add(userInformation.getLabel(), 1, i);
            grid.add(userInformation.getTextField(), 2, i);
            i++;
        }
        dialog.getDialogPane().setContent(grid);

        // Add button to dialog
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);



        // Show dialog
        Optional result = dialog.showAndWait();

        if (result.isPresent()) {

//            actionStatus.setText("Result: " + result.get());
        }
    }

}
