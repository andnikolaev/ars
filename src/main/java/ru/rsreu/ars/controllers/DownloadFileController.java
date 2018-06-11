package ru.rsreu.ars.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/***
 * Обработчик формы загрузки файлов
 */
public class DownloadFileController {
    /***
     * Диалоговое окно для загрузки файлов
     * @param labelName сообщение для текстового поля
     * @param titleText загооловок диалогового окна
     * @param buttonText текст на кнопке
     * @return текстовое поле
     */
    public TextField show(String labelName,String titleText,String buttonText) {
        javafx.scene.control.Dialog dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle(titleText);
        dialog.setHeaderText("Заполните все поля");
        dialog.setResizable(true);
        // Create layout and add to dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        javafx.scene.control.Label label = new javafx.scene.control.Label();
        label.setText(labelName);
        javafx.scene.control.TextField textField = new javafx.scene.control.TextField();
        grid.add(label, 1, 1);
        grid.add(textField, 2, 1);
        dialog.getDialogPane().setContent(grid);

        // Add button to dialog
        ButtonType buttonTypeOk = new ButtonType(buttonText, ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);


        // Show dialog
        Optional result = dialog.showAndWait();

        ButtonType resultButton = (ButtonType) result.get();
        if (!resultButton.getText().equals("Отмена")) {
            return textField;
        }
        return null;
    }

    /***
     * Проверка текстового поля
     * @param textField Текстовое поле
     * @return Результат проверки
     */
    public boolean validate(TextField textField){
        if (textField.getText().trim().equals("")){
            return false;
        }
        return true;
    }
}
