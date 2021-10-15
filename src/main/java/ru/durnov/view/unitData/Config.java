package ru.durnov.view.unitData;

import javafx.collections.ObservableList;

import java.io.IOException;

public interface Config {
    ObservableList<String> items() throws IOException;
}
