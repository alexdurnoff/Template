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
import ru.durnov.view.unitData.Config;
import ru.durnov.view.unitData.ConfigUtil;
import ru.durnov.view.unitData.Numbers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BreakerUnitCreator implements UnitDataCreator {
    private final ObservableList<Node> children;
    private final List<UserPanelData> userPanelDataList;
    private final List<Button> removeButtonList = new ArrayList<>();
    private final Config configUtil;



    public BreakerUnitCreator(ObservableList<Node> children, List<UserPanelData> unitPanelData, Config configUtil) {
        this.children = children;
        this.userPanelDataList = unitPanelData;
        this.configUtil = configUtil;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        TextField textField = new TextField("");
        ComboBox<String> value = createBreakerBox();
        ComboBox<String> breakerType = createBreakerTypeComboBox();
        TextField currentValue = createCurrentTextField();
        UserPanelData userPanelData = new UnitPanelData(
                new Numbers(textField),
                new BreakerRow(value, breakerType, currentValue)
        );
        userPanelDataList.add(userPanelData);
        createNode(value, breakerType, currentValue, textField, userPanelData);
        return userPanelData;
    }

    @Override
    public void clear() {
        this.removeButtonList.forEach(Button::fire);
    }


    public void createNode(ComboBox<String> value,
                           ComboBox<String> breakerType,
                           TextField currentValue,
                           TextField numbersTextField,
                           UserPanelData userPanelData) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        VBox breakerNameVBox = new NodeWithLabelVBox(value, "Тип автомата");
        VBox breakerTypeVBox = new NodeWithLabelVBox(breakerType, "Тип расц.");
        VBox currentVBox = new NodeWithLabelVBox(currentValue, "ток расц.");
        VBox numbersVBox = new NumberVBox(numbersTextField);
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.userPanelDataList != null){
                this.userPanelDataList.remove(userPanelData);
            }
        });
        removeButtonList.add(removeButton);
        VBox removeButtonBox = new NodeWithLabelVBox(removeButton, "Удалить");
        hBox.getChildren().addAll(breakerNameVBox, breakerTypeVBox, currentVBox, numbersVBox, removeButtonBox);
        this.children.add(hBox);
    }

    ComboBox<String> createBreakerBox(){
        ComboBox<String> breakerBox = new ComboBox<>();
        breakerBox.setPrefWidth(100);
        try {
            breakerBox.setItems(configUtil.items());
        } catch (IOException e) {
            breakerBox.setItems(new Breakers().items());
        }
        return breakerBox;
    }


    ComboBox<String> createBreakerTypeComboBox(){
        ComboBox<String> breakerTypeComboBox = new ComboBox<>();
        breakerTypeComboBox.setPrefWidth(70);
        breakerTypeComboBox.setItems(new BreakerTypes().items());
        return breakerTypeComboBox;
    }

    TextField createCurrentTextField(){
        TextField currentField = new TextField();
        currentField.setPrefWidth(50);
        return currentField;
    }
}
