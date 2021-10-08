package ru.durnov.view.unitData.reserv;

import javafx.scene.control.TextField;
import ru.durnov.UserPanelData;
import ru.durnov.view.unitData.Numbers;

public class ReservPanelData implements UserPanelData {
    private final Numbers numbers;

    public ReservPanelData(TextField reservTextField) {
        this.numbers = new Numbers(reservTextField);
    }

    @Override
    public void writeData(Object[][] data) {
        this.numbers.listNumbers().forEach(integer -> {
            Object[] row = data[integer];
            row[1] = "резерв";
        });
    }

    @Override
    public void clear() {

    }
}
