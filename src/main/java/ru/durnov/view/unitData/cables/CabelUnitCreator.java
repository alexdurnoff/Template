package ru.durnov.view.unitData.cables;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ru.durnov.UserPanelData;
import ru.durnov.view.UnitDataCreator;
import ru.durnov.view.UnitPanelData;
import ru.durnov.view.unitData.Numbers;
import ru.durnov.view.unitData.breakers.BreakerRow;

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

    }
}
