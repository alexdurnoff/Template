package ru.durnov;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ru.durnov.view.HeaderPanelData;
import ru.durnov.view.TypePanelData;
import ru.durnov.view.unitData.breakers.BreakerUnitCreator;
import ru.durnov.view.unitData.cables.CabelUnitCreator;
import ru.durnov.view.unitData.reserv.ReservPanelData;
import ru.durnov.view.unitData.uzo.UZOUnitCreator;

import java.util.ArrayList;
import java.util.List;

public class TemplateController {
    private final List<UserPanelData> userPanelDataList = new ArrayList<>();

    @FXML
    private TextField panelName;

    @FXML
    private TextField rowCount;

    @FXML
    private ComboBox<String> breakerMark;

    @FXML
    private Button addBreakerButton;

    @FXML
    private Button addUZOButton;

    @FXML
    private Button addCableButton;

    @FXML
    private VBox breakerVBox;

    @FXML
    private VBox cableVBox;

    @FXML
    private VBox uzoVBox;

    @FXML
    private TextField reservTextField;


    @FXML
    public void savePanel(ActionEvent actionEvent){

    }

    @FXML
    public void clear(ActionEvent actionEvent){

    }

    public void initialize(){
        userPanelDataList.add(new HeaderPanelData(rowCount, panelName, breakerMark));
        List<UserPanelData> breakerPanelList = new ArrayList<>();
        userPanelDataList.add(
                new TypePanelData(
                        breakerPanelList,
                        addBreakerButton,
                        new BreakerUnitCreator(
                                breakerVBox.getChildren(),
                                breakerPanelList
                        )
                )
        );
        List<UserPanelData> cablePanelList = new ArrayList<>();
        userPanelDataList.add(
                new TypePanelData(
                        cablePanelList,
                        addCableButton,
                        new CabelUnitCreator(
                                cableVBox.getChildren(),
                                cablePanelList
                        )
                )
        );

        List<UserPanelData> uzoPanelList = new ArrayList<>();
        userPanelDataList.add(
                new TypePanelData(
                        uzoPanelList,
                        addUZOButton,
                        new UZOUnitCreator(
                                uzoVBox.getChildren(),
                                uzoPanelList
                        )
                )
        );
        userPanelDataList.add(new ReservPanelData(reservTextField));

    }


}
