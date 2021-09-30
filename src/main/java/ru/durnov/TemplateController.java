package ru.durnov;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.durnov.model.RootPanel;

public class TemplateController {
    public RootPanel root;

    @FXML
    void savePanel(ActionEvent actionEvent){
        root.writeToSpreadSheet();
    }

    @FXML
    void clear(ActionEvent actionEvent){
        root.clear();
    }
}
