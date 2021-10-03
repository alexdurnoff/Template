package ru.durnov.view.unitData.uzo;

import ru.durnov.view.Row;

public class UzoRow implements Row {
    private final String uzoType;
    private final String nominalCurrent;
    private final String difCurrent;
    private final String currentType;

    public UzoRow(String uzoType, String nominalCurrent, String difCurrent, String currentType) {
        this.uzoType = uzoType;
        this.nominalCurrent = nominalCurrent;
        this.difCurrent = difCurrent;
        this.currentType = currentType;
    }

    @Override
    public void writeToRow(Object[] dataRow) {
        dataRow[10] = uzoType;
        dataRow[11] = nominalCurrent;
        dataRow[12] = currentType;
        dataRow[13] = difCurrent;
    }
}
