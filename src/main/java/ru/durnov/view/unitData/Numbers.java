package ru.durnov.view.unitData;

import javafx.scene.control.TextField;
import ru.durnov.view.RowsNumbers;

import java.util.List;

public class Numbers implements RowsNumbers {
    private final TextField textField;

    public Numbers(TextField textField) {
        this.textField = textField;
    }

    @Override
    public List<Integer> listNumbers() {
        return null;
    }
}
