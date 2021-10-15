package ru.durnov.view;

import javafx.scene.control.Button;
import ru.durnov.UserPanelData;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TypePanelData implements UserPanelData {
    private final List<UserPanelData> panelDataList;
    private final UnitDataCreator unitDataCreator;


    public TypePanelData(List<UserPanelData> panelDataList,
                         Button button,
                         UnitDataCreator unitDataCreator) {
        this.panelDataList = panelDataList;
        this.unitDataCreator = unitDataCreator;
        button.setOnAction(ae -> {
            unitDataCreator.createUserPanelData(this.panelDataList);
        });
    }


    @Override
    public void writeData(Object[][] data) {
        this.panelDataList.forEach(userPanelData -> {
            userPanelData.writeData(data);
        });
    }

    @Override
    public void clear() {
        this.panelDataList.clear();
        this.unitDataCreator.clear();
    }
}
