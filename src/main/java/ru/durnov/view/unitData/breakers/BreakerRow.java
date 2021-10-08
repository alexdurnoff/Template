package ru.durnov.view.unitData.breakers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.durnov.view.Row;
import ru.durnov.view.unitData.breakers.InstantCurrent;

public class BreakerRow implements Row {
    private final ComboBox<String> value;
    private final ComboBox<String> breakerType;
    private final TextField currentValue;
    private final InstantCurrent instantCurrent;

    public BreakerRow(ComboBox<String> value, ComboBox<String> breakerType, TextField currentValue) {
        this.value = value;
        this.breakerType = breakerType;
        this.currentValue = currentValue;
        this.instantCurrent = new InstantCurrent(breakerType);
    }


    @Override
    public void writeToRow(Object[] dataRow) {
        if (value.getValue() != null) dataRow[2] = value.getValue();
        if (breakerType.getValue() != null) dataRow[3] = breakerType.getValue();
        if (currentValue.getText() != null) dataRow[4] = currentValue.getText();
        try {
            dataRow[5] = String.valueOf(instantCurrent.value() * Integer.parseInt(currentValue.getText()));
        } catch (NumberFormatException e) {
            try {
                dataRow[5] = String.valueOf(instantCurrent.value() * Double.parseDouble(currentValue.getText()));
            } catch (NumberFormatException ignored) {

            }
        }
    }
}
