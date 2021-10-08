package ru.durnov.view.unitData.uzo;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import ru.durnov.UserPanelData;
import ru.durnov.view.*;
import ru.durnov.view.unitData.Numbers;

import java.util.ArrayList;
import java.util.List;

public class UZOUnitCreator implements UnitDataCreator {
    private final ObservableList<Node> children;
    private final List<UserPanelData> uzoPanelList;
    private final List<Button> removeButtonList = new ArrayList<>();


    public UZOUnitCreator(ObservableList<Node> children, List<UserPanelData> uzoPanelList) {
        this.children = children;
        this.uzoPanelList = uzoPanelList;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        TextField numbersTextField = new TextField();
        TextField uzoType = new TextField();
        TextField nominalCurrent = createUzoNominalTextField();
        ComboBox<String> difCurrent = createDifCurrentComboBox();
        ComboBox<String>currentType = createCurrentTypeComboBox();
        UserPanelData userPanelData = new UnitPanelData(
                new Numbers(numbersTextField),
                new UzoRow(uzoType, nominalCurrent, difCurrent, currentType)
        );
        userPanelDataList.add(userPanelData);
        createNode(numbersTextField, uzoType, nominalCurrent, difCurrent, currentType, userPanelData);
        return userPanelData;
    }

    @Override
    public void clear() {
        this.removeButtonList.forEach(Button::fire);
    }

    private ComboBox<String> createCurrentTypeComboBox() {
        ComboBox<String> uzoDifCurrentTypeComboBox = new ComboBox<>(UzoUtils.difCurrentTypeItems());
        uzoDifCurrentTypeComboBox.setValue("AC");
        return uzoDifCurrentTypeComboBox;
    }

    private ComboBox<String> createDifCurrentComboBox() {
        return new ComboBox<>(UzoUtils.difCurrentItems());
    }

    public void createNode(TextField numbersTextField,
                           TextField uzoType,
                           TextField nominalCurrent,
                           ComboBox<String> difCurrent,
                           ComboBox<String> currentType,
                           UserPanelData userPanelData)  {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        Label uzoTypeLabel = new Label("Тип УЗО");
        uzoTypeLabel.setTextAlignment(TextAlignment.CENTER);
        uzoTypeLabel.setAlignment(Pos.CENTER);
        uzoTypeLabel.setPrefWidth(200);
        HBox uzoTypeHBox = createUzoTypeHBox(uzoType);
        VBox uzoTypeVBox = new NodeWithLabelVBox(uzoTypeLabel, uzoTypeHBox);
        VBox uzoNominalCurrentVBox = new NodeWithLabelVBox(nominalCurrent, "Ном. ток");
        VBox uzoDifCurrentVBox = new NodeWithLabelVBox(difCurrent, "Дифф. ток");
        VBox uzoDifCurrentTypeVBox = new NodeWithLabelVBox(currentType, "Тип дифф. тока");
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.uzoPanelList != null){
                this.uzoPanelList.remove(userPanelData);
            }
        });
        removeButtonList.add(removeButton);
        VBox linesBox = new NumberVBox(numbersTextField);
        Label removeLabel = new Label("Удалить");
        VBox removeButtonBox = new VBox(removeLabel,removeButton);
        removeButtonBox.setSpacing(3);
        hBox.getChildren().addAll(uzoTypeVBox, uzoNominalCurrentVBox, uzoDifCurrentVBox, uzoDifCurrentTypeVBox, linesBox, removeButtonBox);
        this.children.add(hBox);
    }

    HBox createUzoTypeHBox(TextField uzoType){
        ComboBox<String> uzoTypeComboBox = new ComboBox<>(UzoUtils.typeItems());
        uzoTypeComboBox.setPrefWidth(100);
        TextField uzoTypeField = new TextField();
        uzoTypeField.setPrefWidth(100);
        uzoTypeComboBox.setOnAction(ae -> uzoTypeField.setText(uzoTypeComboBox.getValue()));
        uzoTypeField.textProperty().addListener(((observable, oldValue, newValue) -> {
            uzoType.setText(newValue);
        }));
        HBox uzoTypeHBox = new HBox(uzoTypeComboBox, uzoTypeField);
        uzoTypeHBox.setSpacing(5);
        return uzoTypeHBox;
    }

    TextField createUzoNominalTextField(){
        TextField uzoNominalCurrentTextField = new TextField();
        uzoNominalCurrentTextField.setPrefWidth(50);
        return uzoNominalCurrentTextField;
    }
}
