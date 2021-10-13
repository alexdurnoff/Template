package ru.durnov.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Элементы маркировки автоматов
 */
public class BreakerMarkItems {
    private final ObservableList<String> items = FXCollections.observableArrayList();


    public BreakerMarkItems(){
        this.items.addAll("QF", "1QF", "2QF", "3QF", "4QF", "QF1.", "QF2.", "QF3.", "QF4.");
    }

    public ObservableList<String> items() {
        return this.items;
    }
}
