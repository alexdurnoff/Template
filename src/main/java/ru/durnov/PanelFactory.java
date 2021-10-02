package ru.durnov;

import javafx.scene.control.Button;
import ru.durnov.view.TypePanelData;
import ru.durnov.view.UnitDataCreator;

import java.util.ArrayList;
import java.util.List;

public class PanelFactory implements UserDataFactory{
    private final UnitDataCreator unitDataCreator;
    private final Button button;

    public PanelFactory(UnitDataCreator unitDataCreator, Button button) {
        this.unitDataCreator = unitDataCreator;
        this.button = button;
    }


    @Override
    public UserPanelData createPanelData() {
        List<UserPanelData> panelDataList = new ArrayList<>();
        return new TypePanelData(panelDataList,button, unitDataCreator);
    }
}
