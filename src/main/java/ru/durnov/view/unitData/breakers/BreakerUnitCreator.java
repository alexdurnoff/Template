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
import ru.durnov.view.NodeWithLabelVBox;
import ru.durnov.view.NumberVBox;
import ru.durnov.view.UnitDataCreator;
import ru.durnov.view.UnitPanelData;
import ru.durnov.view.unitData.Numbers;

import java.util.List;

public class BreakerUnitCreator implements UnitDataCreator {
    private final ObservableList<Node> children;
    private final List<UserPanelData> userPanelDataList;
    private String value = "";
    private String breakerType = "";
    private String currentValue = "";
    private TextField textField;


    public BreakerUnitCreator(ObservableList<Node> children, List<UserPanelData> unitPanelData) {
        this.children = children;
        this.userPanelDataList = unitPanelData;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        this.textField = new TextField();
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
        VBox breakerNameVBox = new NodeWithLabelVBox(createBreakerBox(), "Тип автомата");
        VBox breakerTypeVBox = new NodeWithLabelVBox(createBreakerTypeComboBox(), "Тип расцепителя");
        VBox currentVBox = new NodeWithLabelVBox(createCurrentTextField(), "ток расцепителя");
        VBox numbersVBox = new NumberVBox(this.textField);
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.userPanelDataList != null){
                this.userPanelDataList.remove(userPanelData);
            }
        });
        VBox removeButtonBox = new NodeWithLabelVBox(removeButton, "Удалить");
        hBox.getChildren().addAll(breakerNameVBox, breakerTypeVBox, currentVBox, numbersVBox, removeButtonBox);
        this.children.add(hBox);
    }

    ComboBox<String> createBreakerBox(){
        ComboBox<String> breakerBox = new ComboBox<>();
        breakerBox.setPrefWidth(150);
        breakerBox.setItems(new Breakers().items());
        breakerBox.setOnAction(ae -> value = breakerBox.getValue());
        return breakerBox;
    }


    ComboBox<String> createBreakerTypeComboBox(){
        ComboBox<String> breakerTypeComboBox = new ComboBox<>();
        breakerTypeComboBox.setPrefWidth(150);
        breakerTypeComboBox.setItems(new BreakerTypes().items());
        breakerTypeComboBox.setOnAction(ae -> breakerType = breakerTypeComboBox.getValue());
        return breakerTypeComboBox;
    }

    TextField createCurrentTextField(){
        TextField currentField = new TextField();
        currentField.setPrefWidth(150);
        currentField.setOnAction(ae -> {
            currentValue = new CurrentValue(currentField).value();
        });
        return currentField;
    }
}
