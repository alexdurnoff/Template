package ru.durnov.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LinesVBox extends VBox {
    private final TextField textField;

    public LinesVBox(TextField textField) {
        this.textField = textField;
        Label label = new Label("Номера");
        this.getChildren().addAll(label, textField);
        this.setSpacing(5);
    }
}
