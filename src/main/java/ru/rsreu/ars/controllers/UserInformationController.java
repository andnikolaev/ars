package ru.rsreu.ars.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ru.rsreu.ars.core.TemplateIdentifier;
import ru.rsreu.ars.core.beans.UserInformation;

import java.util.*;

public class UserInformationController {
    private Map<String, String> templateIdentifiersWithType;
    private Map<String, String> templateIdentifiersWithText;
    private Map<String, UserInformation> userInformationMap;
    private List<UserInformation> userInformationList;

    public UserInformationController(Map<String, String> templateIdentifiersWithType, Map<String, String> templateIdentifiersWithText) {
        this.templateIdentifiersWithType = templateIdentifiersWithType;
        this.templateIdentifiersWithText = templateIdentifiersWithText;
    }

    public Map<String, UserInformation> show() {

        // Custom dialog
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Информация о пользователе");
        dialog.setHeaderText("Заполните все поля ФИО студента: Группа: ");
        dialog.setResizable(true);

        //TODO Переделать в map <Key, UserInformations>
        if (userInformationMap == null || userInformationList == null) {
            this.userInformationMap = new HashMap<>();
            this.userInformationList = new ArrayList<>();
            for (Map.Entry<String, String> entry : templateIdentifiersWithType.entrySet()) {
                if (TemplateIdentifier.checkKeyTagOnUnresolved(entry.getKey())) {
                    Label label = new Label(templateIdentifiersWithText.get(entry.getKey()));
                    TextField textField = new TextField();
                    UserInformation userInformation = new UserInformation(entry.getKey(), label, textField);
                    userInformationList.add(userInformation);
                    userInformationMap.put(entry.getKey(), userInformation);
                    textField.appendText(userInformationMap.get(entry.getKey()).getTextField().getText());
                }
            }
        }


        // Create layout and add to dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        int i = 1;
        for (UserInformation userInformation : userInformationList) {
            grid.add(userInformation.getLabel(), 1, i);
            grid.add(userInformation.getTextField(), 2, i);
            i++;
        }
        dialog.getDialogPane().setContent(grid);

        // Add button to dialog
        ButtonType buttonTypeOk = new ButtonType("Ок", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);


        // Show dialog
        Optional result = dialog.showAndWait();

        ButtonType resultButton = (ButtonType) result.get();
        if (!resultButton.getText().equals("Отмена")) {
            return userInformationMap;
        }
        return null;
    }

    public boolean checkUserInformation(Map<String, UserInformation> userInformation) {
        boolean result = true;
        if (userInformation.size() == 0) {
            result = false;
        }
        for (Map.Entry<String, UserInformation> entry : userInformation.entrySet()) {
            if (TemplateIdentifier.checkKeyTagOnUnresolved(entry.getKey())) {
                if (entry.getValue().getTextField().getText().trim().isEmpty()) {
                    result = false;
                }
            }
        }
        return result;
    }


}
