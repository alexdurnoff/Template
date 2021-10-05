package ru.durnov.view.unitData;

import javafx.scene.control.TextField;
import ru.durnov.view.RowsNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Numbers implements RowsNumbers {
    private final TextField textField;

    public Numbers(TextField textField) {
        this.textField = textField;
    }

    @Override
    public List<Integer> listNumbers() {
        return new StringRow(this.textField.getText().trim()).listNumbers();
    }
}
