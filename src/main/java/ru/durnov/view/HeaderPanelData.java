package ru.durnov.view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.durnov.UserPanelData;
import ru.durnov.view.unitData.breakers.BreakerMark;

public class HeaderPanelData implements UserPanelData {
    private final TextField rowCount;
    private final TextField panelName;
    private final ComboBox<String> breakerComboBox;


    public HeaderPanelData(TextField rowCount, TextField panelName, ComboBox<String> breakerComboBox) {
        this.rowCount = rowCount;
        this.panelName = panelName;
        this.breakerComboBox = breakerComboBox;
    }

    @Override
    public void writeData(Object[][] data) {
        try {
            int count = Integer.parseInt(rowCount.getText());
            Object[] headerRow = data[0];
            headerRow[1] = panelName.getText();
            BreakerMark breakerMark = new BreakerMark(breakerComboBox.getValue());
            for (int i = 1; i <count; i++){
                Object[] row = data[i];
                breakerMark.writeMark(row);
            }
        } catch (NumberFormatException ignored) {

        }
    }
}
