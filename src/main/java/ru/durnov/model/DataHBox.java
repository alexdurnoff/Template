package ru.durnov.model;

import javafx.scene.layout.HBox;
import ru.durnov.UserDataPanel;

public class DataHBox extends HBox implements UserDataPanel {
    CellValue cellValue;
    RowsNumbers rowsNumbers;

    @Override
    public void writeData(Object[][] data) {
        rowsNumbers.listNumbers().forEach(number -> {
            cellValue.writeToRow(data[number]);
        });
    }
}
