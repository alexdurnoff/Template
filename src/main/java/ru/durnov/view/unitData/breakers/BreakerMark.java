package ru.durnov.view.unitData.breakers;

public class BreakerMark {
    private final String startsWith;
    private int currentRow = 0;

    public BreakerMark(String value) {
        this.startsWith = value;
    }

    public void writeMark(Object[] row){
        currentRow++;
        row[0] = startsWith + currentRow;
    }
}
