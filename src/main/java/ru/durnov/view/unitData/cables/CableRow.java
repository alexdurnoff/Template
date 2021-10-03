package ru.durnov.view.unitData.cables;

import ru.durnov.view.Row;

public class CableRow implements Row {
    private final String type;
    private final String conductors;
    private final FazaValue fazaValue = new FazaValue();

    public CableRow(String type, String conductors) {
        this.type = type;
        this.conductors = conductors;
    }

    @Override
    public void writeToRow(Object[] dataRow) {
        dataRow[14] = type + " " + conductors;
        dataRow[15] = new ConductorsCount(conductors).value();
        dataRow[16] = fazaValue.value();
    }
}

class ConductorsCount{
    private final String conductors;

    ConductorsCount(String conductors) {
        this.conductors = conductors;
    }

    String value(){
        if (conductors.startsWith("3х")) return "3";
        if (conductors.startsWith("3(1х")) return "3";
        if (conductors.startsWith("2х")) return "2";
        if (conductors.startsWith("2(1х")) return "2";
        if (conductors.startsWith("4х")) return "4";
        if (conductors.startsWith("4(1х")) return "4";
        return "5";
    }
}
