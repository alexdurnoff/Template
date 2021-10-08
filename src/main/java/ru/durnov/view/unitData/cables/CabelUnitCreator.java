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
import ru.durnov.view.NodeWithLabelVBox;
import ru.durnov.view.NumberVBox;
import ru.durnov.view.UnitDataCreator;
import ru.durnov.view.UnitPanelData;
import ru.durnov.view.unitData.Numbers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CabelUnitCreator implements UnitDataCreator {
    private final ObservableList<Node> children;
    private final List<UserPanelData> cablePanelList;
    private final List<Button> removeButtonList = new ArrayList<>();

    public CabelUnitCreator(ObservableList<Node> children, List<UserPanelData> cablePanelList) {
        this.children = children;
        this.cablePanelList = cablePanelList;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        TextField numbersTextField = new TextField();
        TextField conductors = new TextField();
        ComboBox<String> type = createCableTypeComboBox();
        UserPanelData userPanelData = new UnitPanelData(
                new Numbers(numbersTextField),
                new CableRow(type, conductors)
        );
        userPanelDataList.add(userPanelData);
        createNode(numbersTextField, userPanelData, type, conductors);
        return userPanelData;
    }

    @Override
    public void clear() {
        this.removeButtonList.forEach(Button::fire);
    }


    public void createNode(TextField numbersTextField,
                           UserPanelData userPanelData,
                           ComboBox<String> type,
                           TextField conductors) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        VBox cableTypeVBox = new NodeWithLabelVBox(createCableTypeComboBox(), "Тип кабеля");
        VBox conductorsVBox = createConductorsVBox(conductors);
        VBox numbersVBox= new NumberVBox(numbersTextField);
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.cablePanelList != null){
                this.cablePanelList.remove(userPanelData);
            }
        });
        removeButtonList.add(removeButton);
        Label removeLabel = new Label("Удалить");
        VBox removeButtonBox = new VBox(removeLabel,removeButton);
        removeButtonBox.setSpacing(3);
        hBox.getChildren().addAll(cableTypeVBox, conductorsVBox, numbersVBox, removeButtonBox);
        this.children.add(hBox);
    }

    ComboBox<String> createCableTypeComboBox(){
        return new ComboBox<>(CableUtils.typeItems());
    }

    VBox createConductorsVBox(TextField conductors){
        ComboBox<String> conductorsComboBox = new ComboBox<>(CableUtils.conductorsItems());
        conductorsComboBox.setOnAction(ae -> conductors.setText(conductorsComboBox.getValue()));
        HBox conductorsTextFieldHBox = new HBox(conductorsComboBox, conductors);
        conductorsTextFieldHBox.setSpacing(5);
        VBox conductorsVBox = new NodeWithLabelVBox(conductorsTextFieldHBox, "Количество и сечение проводников");
        conductorsVBox.setSpacing(5);
        return conductorsVBox;
    }
}
