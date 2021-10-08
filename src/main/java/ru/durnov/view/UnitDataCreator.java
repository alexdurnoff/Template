package ru.durnov.view;

import javafx.scene.Node;
import ru.durnov.UserPanelData;

import java.util.List;

public interface UnitDataCreator {
    UserPanelData createUserPanelData(List<UserPanelData> userPanelDataList);
    void clear();
}
