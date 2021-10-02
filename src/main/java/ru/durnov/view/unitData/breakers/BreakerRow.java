package ru.durnov.view.unitData.breakers;

import ru.durnov.view.Row;
import ru.durnov.view.unitData.breakers.InstantCurrent;

public class BreakerRow implements Row {
    private final String value;
    private final String breakerType;
    private final String currentValue;
    private final InstantCurrent instantCurrent;

    public BreakerRow(String value, String breakerType, String currentValue) {
        this.value = value;
        this.breakerType = breakerType;
        this.currentValue = currentValue;
        this.instantCurrent = new InstantCurrent(breakerType);
    }


    @Override
    public void writeToRow(Object[] dataRow) {
        dataRow[2] = value;
        dataRow[3] = breakerType;
        dataRow[4] = currentValue;
        try {
            dataRow[5] = String.valueOf(instantCurrent.value() * Integer.parseInt(currentValue));
        } catch (NumberFormatException e) {
            try {
                dataRow[5] = String.valueOf(instantCurrent.value() * Double.parseDouble(currentValue));
            } catch (NumberFormatException ignored) {

            }
        }
    }
}
