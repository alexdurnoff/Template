package ru.durnov.model;

import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import ru.durnov.UserDataPanel;

public class RootPanel extends AnchorPane {
    ObservableList<UserDataPanel> dataPanels;
    SpreadSheet spreadSheet;

    void writeToSpreadSheet(){
        Object[][] data = spreadSheet.data();
        dataPanels.forEach(dataPanel -> dataPanel.writeData(data));
    }
}
