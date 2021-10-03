package ru.durnov.view.unitData.uzo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UzoUtils {
    public static ObservableList<String> typeItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("ABB F202");
        list.add("ABB F204");
        list.add("ABB DS202");
        list.add("ABB DS204");
        list.add("ABB DS203NC");
        return list;
    }

    public static ObservableList<String> difCurrentItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("6");
        list.add("10");
        list.add("30");
        list.add("100");
        list.add("300");
        list.add("500");
        list.add("1000");
        return list;
    }

    public static ObservableList<String> difCurrentTypeItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("A");
        list.add("AC");
        return list;
    }
}
