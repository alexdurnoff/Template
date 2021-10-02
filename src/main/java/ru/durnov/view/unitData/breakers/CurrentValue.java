package ru.durnov.view.unitData.breakers;

import javafx.scene.control.TextField;

public class CurrentValue {
    private final TextField currentField;

    public CurrentValue(TextField currentField) {
        this.currentField = currentField;
    }

    public String value() {
        String value = "";
        try {
            value = String.valueOf(Integer.parseInt(currentField.getText()));
        } catch (NumberFormatException e) {
            try {
                value = String.valueOf(Double.parseDouble(currentField.getText().replace(",", ".")));
            } catch (NumberFormatException ignored) {

            }
        }
        return value;
    }
}
