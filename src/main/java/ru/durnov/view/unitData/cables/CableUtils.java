package ru.durnov.view.unitData.cables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CableUtils {


    public static ObservableList<String> typeItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("ВВГнг(А)-LS");
        list.add("ВВГнг(А)-FRLS");
        list.add("ППГнг(А)-HF");
        list.add("ППГнг(А)-FRHF");
        return list;
    }

    public static ObservableList<String> conductorsItems() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("3х1,5");
        list.add("3х2,5");
        list.add("3х4");
        list.add("3х6");
        list.add("3х10");
        list.add("3х16");
        list.add("3х25");
        list.add("5х1,5");
        list.add("5х2,5");
        list.add("5х4");
        list.add("5х6");
        list.add("5х10");
        list.add("5х16");
        list.add("5х25");
        list.add("5х35");
        list.add("5х50");
        list.add("5х70");
        list.add("5х95");
        list.add("5х120");
        list.add("5х150");
        list.add("5х185");
        list.add("5х240");
        return list;
    }
}
