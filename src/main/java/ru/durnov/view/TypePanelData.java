package ru.durnov.view;

import javafx.scene.control.Button;
import ru.durnov.UserPanelData;

import java.util.ArrayList;
import java.util.List;

public class TypePanelData implements UserPanelData {
    private final List<UserPanelData> panelDataList;


    public TypePanelData(List<UserPanelData> panelDataList,
                         Button button,
                         UnitDataCreator unitDataCreator) {
        this.panelDataList = panelDataList;
        button.setOnAction(ae -> {
            UserPanelData userPanelData = unitDataCreator.createUserPanelData(this.panelDataList);
            unitDataCreator.createNode(userPanelData);
        });
    }


    @Override
    public void writeData(Object[][] data) {
        this.panelDataList.forEach(userPanelData -> {
            userPanelData.writeData(data);
        });
    }
}
