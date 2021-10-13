package ru.durnov.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Breakers {
    private final static ObservableList<String> list = FXCollections.observableArrayList(
            "Schneider Electric EasyPact CVS400F",
            "Schneider Electric EasyPact CVS250F",
            "Schneider Electric EasyPact CVS250B",
            "Schneider Electric EasyPact CVS160F",
            "Schneider Electric EasyPact IC60L",
            "Schneider Electric iDPN N Vigi"
    );


    public ObservableList<String> items() {
        return list;
    }
}
