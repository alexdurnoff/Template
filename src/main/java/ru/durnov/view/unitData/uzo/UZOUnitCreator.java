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
import ru.durnov.view.LinesVBox;
import ru.durnov.view.UnitDataCreator;
import ru.durnov.view.UnitPanelData;
import ru.durnov.view.unitData.Numbers;
import ru.durnov.view.unitData.breakers.BreakerRow;

import java.util.List;

public class UZOUnitCreator implements UnitDataCreator {
    private final ObservableList<Node> children;
    private final List<UserPanelData> uzoPanelList;
    private TextField textField;
    private String uzoType = "";
    private String nominalCurrent = "";
    private String difCurrent = "";
    private String currentType = "AC";


    public UZOUnitCreator(ObservableList<Node> children, List<UserPanelData> uzoPanelList) {
        this.children = children;
        this.uzoPanelList = uzoPanelList;
    }

    @Override
    public UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList) {
        this.textField = new TextField();
        UserPanelData userPanelData = new UnitPanelData(
                new Numbers(this.textField),
                new UzoRow(uzoType, nominalCurrent, difCurrent, currentType)
        );
        userPanelDataList.add(userPanelData);
        return userPanelData;
    }

    @Override
    public void createNode(UserPanelData userPanelData) {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        Label uzoTypeLabel = new Label("Тип УЗО");
        uzoTypeLabel.setTextAlignment(TextAlignment.CENTER);
        uzoTypeLabel.setAlignment(Pos.CENTER);
        uzoTypeLabel.setPrefWidth(200);
        ComboBox<String> uzoTypeComboBox = new ComboBox<>(UzoUtils.typeItems());
        uzoTypeComboBox.setPrefWidth(100);
        TextField uzoTypeField = new TextField();
        uzoTypeField.setPrefWidth(100);
        uzoTypeComboBox.setOnAction(ae -> uzoTypeField.setText(uzoTypeComboBox.getValue()));
        uzoTypeField.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.uzoType = newValue;
        }));
        HBox uzoTypeHBox = new HBox(uzoTypeComboBox, uzoTypeField);
        uzoTypeHBox.setSpacing(5);
        VBox uzoTypeVBox = new VBox(uzoTypeLabel, uzoTypeHBox);
        uzoTypeVBox.setSpacing(5);
        Label uzoNominalCurrentLabel = new Label("Ном. ток");
        TextField uzoNominalCurrentTextField = new TextField();
        uzoNominalCurrentTextField.setPrefWidth(50);
        uzoNominalCurrentTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.nominalCurrent = uzoNominalCurrentTextField.getText();
        }));
        VBox uzoNominalCurrentVBox = new VBox(uzoNominalCurrentLabel, uzoNominalCurrentTextField);
        uzoNominalCurrentVBox.setSpacing(5);
        Label uzoDifCurrentLabel = new Label("Дифф. ток");
        ComboBox<String> uzoDifCurrentComboBox = new ComboBox<>(UzoUtils.difCurrentItems());
        VBox uzoDifCurrentVBox = new VBox(uzoDifCurrentLabel, uzoDifCurrentComboBox);
        uzoDifCurrentVBox.setSpacing(5);
        Label uzoDifCurrentTypeLabel = new Label("Тип дифф. тока");
        ComboBox<String> uzoDifCurrentTypeComboBox = new ComboBox<>(UzoUtils.difCurrentTypeItems());
        uzoDifCurrentTypeComboBox.setValue("AC");
        VBox uzoDifCurrentTypeVBox = new VBox(uzoDifCurrentTypeLabel, uzoDifCurrentTypeComboBox);
        uzoDifCurrentTypeVBox.setSpacing(5);
        Button removeButton = new Button("-");
        removeButton.setOnAction(ae -> {
            this.children.remove(hBox);
            if (this.uzoPanelList != null){
                this.uzoPanelList.remove(userPanelData);
            }
        });
        VBox linesBox = new LinesVBox(textField);
        Label removeLabel = new Label("Удалить");
        VBox removeButtonBox = new VBox(removeLabel,removeButton);
        removeButtonBox.setSpacing(3);
        hBox.getChildren().addAll(uzoTypeVBox, uzoNominalCurrentVBox, uzoDifCurrentVBox, uzoDifCurrentTypeVBox, linesBox, removeButtonBox);
        this.children.add(hBox);
    }
}
