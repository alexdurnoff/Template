package ru.durnov.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NumberVBox extends VBox {
    private final Label label = new Label("Номера строк");
    private final TextField textField;

    public NumberVBox(TextField textField) {
        this.textField = textField;
        this.getChildren().addAll(label, textField);
        this.setSpacing(3);
    }
}
