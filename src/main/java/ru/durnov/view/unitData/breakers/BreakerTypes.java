package ru.durnov.view.unitData.breakers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BreakerTypes {


    public ObservableList<String> items() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("C", "B", "D", "МД", "EKIP LS/I", "Micrologic 2.0", "Micrologic 2.2",
                "Micrologic 5.0E", "ETS 2.3", "ETS5S", "PR221DS", "EKIP touch LS/I",
                "ETS23", "ETS33", "ETS43", "OCR", "OCGR");
        return list;
    }
}
