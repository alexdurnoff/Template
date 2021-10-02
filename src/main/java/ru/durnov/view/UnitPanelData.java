package ru.durnov.view;

import ru.durnov.UserPanelData;

public class UnitPanelData implements UserPanelData {
    private final RowsNumbers rowsNumbers;
    private final Row row;

    public UnitPanelData(RowsNumbers rowsNumbers, Row row) {
        this.rowsNumbers = rowsNumbers;
        this.row = row;
    }


    @Override
    public void writeData(Object[][] data) {
        rowsNumbers.listNumbers().forEach(number -> {
            row.writeToRow(data[number]);
        });
    }
}
