package ru.durnov.view.unitData.purpose;

import javafx.scene.control.TextField;

public class PurposeMark {
    private final TextField textField;
    private int currentRow = 0;

    public PurposeMark(TextField textField) {
        this.textField = textField;
    }

    public void writePurpose(Object[] row) {
        currentRow++;
        row[1] = textField.getText() + currentRow;
    }
}
