package ru.rsreu.ars.core.beans;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserInformation {
    private String key;
    private Label label;
    private TextField textField;

    public UserInformation(String key, Label label, TextField textField) {
        this.key = key;
        this.label = label;
        this.textField = textField;
    }

    public String getKey() {
        return key;
    }

    public Label getLabel() {
        return label;
    }

    public TextField getTextField() {
        return textField;
    }
}
