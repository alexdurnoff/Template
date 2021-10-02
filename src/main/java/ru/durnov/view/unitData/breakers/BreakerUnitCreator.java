package ru.durnov.view.unitData.breakers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.durnov.UserPanelData;
import ru.durnov.model.Breakers;
import ru.durnov.view.UnitDataCreator;
import ru.durnov.view.UnitPanelData;
import ru.durnov.view.unitData.Numbers;

import java.util.List;

public class BreakerUnitCreator implements UnitDataCreator {
    private final TextField textField = new TextField();
    private final ObservableList<Node> children;
    private final List<UserPanelData> userPanelDataList;
    private String value = "";
    private String breakerType = "";
    private String currentValue = "";


    public BreakerUnitCreator(ObservableList<Node> children, List<UserPanelData> unitPanelData) {
        this.children = children;
        this.userPanelDataList = unitPanelData;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        UserPanelData userPanelData = new UnitPanelData(
                new Numbers(textField),
                new BreakerRow(value, breakerType, currentValue)
        );
        userPanelDataList.add(userPanelData);
        return userPanelData;
    }

    @Override
    public void createNode(UserPanelData userPanelData) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        ComboBox<String> breakerBox = new ComboBox<>();
        breakerBox.setItems(new Breakers().items());
        breakerBox.setOnAction(ae -> value = breakerBox.getValue());
        Label label = new Label("Тип автомата");
        VBox breakerNameVBox = new VBox(label, breakerBox);
        breakerNameVBox.setSpacing(3);
        ComboBox<String> breakerTypeComboBox = new ComboBox<>();
        breakerTypeComboBox.setItems(new BreakerTypes().items());
        breakerTypeComboBox.setOnAction(ae -> breakerType = breakerTypeComboBox.getValue());
        VBox breakerTypeVBox = new VBox();
        breakerTypeVBox.setSpacing(3);
        breakerTypeVBox.getChildren().addAll(new Label("Тип расцепителя"), breakerTypeComboBox);
        TextField currentField = new TextField();
        currentField.setOnAction(ae -> {
            currentValue = new CurrentValue(currentField).value();
        });
        VBox currentVBox = new VBox();
        currentVBox.setSpacing(3);
        currentVBox.getChildren().addAll(new Label("Ток расцепителя"), currentField);
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.userPanelDataList != null){
                this.userPanelDataList.remove(userPanelData);
            }
        });
        hBox.getChildren().addAll(breakerNameVBox, breakerTypeVBox, currentVBox, removeButton);
        this.children.add(hBox);
    }
}
