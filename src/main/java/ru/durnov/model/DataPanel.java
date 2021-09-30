package ru.durnov.model;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ru.durnov.UserDataPanel;

public class DataPanel extends ScrollPane implements UserDataPanel {
    private ObservableList<UserDataPanel> dataHBoxes;
    private VBox vBox;
    private Button addButton = new Button("+");

    @Override
    public void writeData(Object[][] data) {
        this.dataHBoxes.forEach(hBox -> hBox.writeData(data));
    }

    @Override
    public void clear() {
        this.dataHBoxes.clear();
        this.vBox.getChildren().clear();
    }

    @Override
    public void add() {

    }

}
