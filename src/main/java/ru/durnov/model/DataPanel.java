package ru.durnov.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import ru.durnov.UserDataPanel;

public class DataPanel extends ScrollPane implements UserDataPanel {
    private ObservableList<UserDataPanel> dataHBoxes;

    @Override
    public void writeData(Object[][] data) {
        this.dataHBoxes.forEach(hBox -> hBox.writeData(data));
    }

}
