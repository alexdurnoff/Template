package ru.durnov.view.unitData.uzo;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.durnov.view.Row;

public class UzoRow implements Row {
    private final TextField uzoType;
    private final TextField nominalCurrent;
    private final ComboBox<String> difCurrent;
    private final ComboBox<String> currentType;

    public UzoRow(TextField uzoType,
                  TextField nominalCurrent,
                  ComboBox<String> difCurrent,
                  ComboBox<String> currentType) {
        this.uzoType = uzoType;
        this.nominalCurrent = nominalCurrent;
        this.difCurrent = difCurrent;
        this.currentType = currentType;
    }

    @Override
    public void writeToRow(Object[] dataRow) {
        if (uzoType.getText() != null) dataRow[10] = uzoType.getText();
        if (this.nominalCurrent.getText() != null) dataRow[11] = nominalCurrent;
        dataRow[12] = currentType;
        dataRow[13] = difCurrent;
    }
}
