package ru.durnov.view.unitData.cables;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.durnov.UserPanelData;
import ru.durnov.view.UnitDataCreator;
import ru.durnov.view.UnitPanelData;
import ru.durnov.view.unitData.Numbers;

import java.util.List;

public class CabelUnitCreator implements UnitDataCreator {
    private final ObservableList<Node> children;
    private final List<UserPanelData> cablePanelList;
    private String type = "";
    private String conductors = "";



    public CabelUnitCreator(ObservableList<Node> children, List<UserPanelData> cablePanelList) {
        this.children = children;
        this.cablePanelList = cablePanelList;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        UserPanelData userPanelData = new UnitPanelData(
                new Numbers(new TextField()),
                new CableRow(type, conductors)
        );
        userPanelDataList.add(userPanelData);
        return userPanelData;
    }

    @Override
    public void createNode(UserPanelData userPanelData) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        Label cableTypeLabel = new Label("Тип кабеля");
        ComboBox<String> cableTypeComboBox = new ComboBox<>(CableUtils.typeItems());
        cableTypeComboBox.setOnAction(ae -> type=cableTypeComboBox.getValue());
        VBox cableTypeVBox = new VBox(cableTypeLabel, cableTypeComboBox);
        cableTypeVBox.setSpacing(5);
        Label conductorsLabel = new Label("Количество и сечение проводников");
        ComboBox<String> conductorsComboBox = new ComboBox<>(CableUtils.conductorsItems());
        TextField conductorsTextField = new TextField();
        conductorsTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.conductors = newValue;
        }));
        conductorsComboBox.setOnAction(ae -> conductorsTextField.setText(cableTypeComboBox.getValue()));
        HBox conductorsTextFieldHBox = new HBox(conductorsComboBox, conductorsTextField);
        conductorsTextFieldHBox.setSpacing(5);
        VBox conductorsVBox = new VBox(conductorsLabel, conductorsTextFieldHBox);
        conductorsVBox.setSpacing(5);
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.cablePanelList != null){
                this.cablePanelList.remove(userPanelData);
            }
        });
        Label removeLabel = new Label("Удалить");
        VBox removeButtonBox = new VBox(removeLabel,removeButton);
        removeButtonBox.setSpacing(3);
        hBox.getChildren().addAll(cableTypeVBox, conductorsVBox, removeButtonBox);
        this.children.add(hBox);
    }
}
