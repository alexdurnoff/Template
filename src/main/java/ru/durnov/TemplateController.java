package ru.durnov;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.uno.Exception;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ru.durnov.model.BreakerMarkItems;
import ru.durnov.view.HeaderPanelData;
import ru.durnov.view.TypePanelData;
import ru.durnov.view.unitData.ConfigUtil;
import ru.durnov.view.unitData.breakers.BreakerUnitCreator;
import ru.durnov.view.unitData.cables.CabelUnitCreator;
import ru.durnov.view.unitData.reserv.ReservPanelData;
import ru.durnov.view.unitData.uzo.UZOUnitCreator;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplateController {
    private final List<UserPanelData> userPanelDataList = new ArrayList<>();
    private Path path;

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
    private TextField purposeMark;

    @FXML
    public void savePanel(ActionEvent actionEvent) throws BootstrapException, Exception {
        try {
            int count = Integer.parseInt(this.rowCount.getText());
            Object[][] data = new Object[count+2][19];
            writeDefaultDataValues(data);
            this.userPanelDataList.forEach(userPanelData -> {
                userPanelData.writeData(data);
            });
            int startRow = new StartRow(ControlRemoteOffice.getCurrentSpreadsheet("Черновик")).startRow();
            XSpreadsheet xSpreadsheet = ControlRemoteOffice.getCurrentSpreadsheet("Черновик");
            ControlRemoteOffice.copyDataArrayToSpreadsheet(data, 0, startRow, 18, xSpreadsheet);
        } catch (NumberFormatException | Exception | BootstrapException e) {
            System.exit(-1);
        }
    }

    /**
     * Заполняем массив пустыми строками
     * @param data
     */
    private void writeDefaultDataValues(Object[][] data) {
        for (Object[] dataRow : data) {
            Arrays.fill(dataRow, "");
        }
    }

    @FXML
    public void clear(ActionEvent actionEvent){
        this.userPanelDataList.forEach(UserPanelData::clear);
    }

    public void initialize(){
        this.path = setupPath();
        breakerMark.setItems(new BreakerMarkItems().items());
        userPanelDataList.add(new HeaderPanelData(rowCount, panelName, purposeMark, breakerMark));
        List<UserPanelData> breakerPanelList = new ArrayList<>();
        userPanelDataList.add(
                new TypePanelData(
                        breakerPanelList,
                        addBreakerButton,
                        new BreakerUnitCreator(
                                breakerVBox.getChildren(),
                                breakerPanelList,
                                new ConfigUtil("breakers", path)
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
                                cablePanelList,
                                new ConfigUtil("cables", path)
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
                                uzoPanelList,
                                new ConfigUtil("uzo", path)
                        )
                )
        );
        userPanelDataList.add(new ReservPanelData(reservTextField));

    }

    private Path setupPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбираем конфигурационный файл");
        File file = fileChooser.showOpenDialog(panelName.getScene().getWindow());
        return file.toPath();
    }

    static class StartRow{
        private final XSpreadsheet xSpreadsheet;

        public StartRow(XSpreadsheet xSpreadsheet) {
            this.xSpreadsheet = xSpreadsheet;
        }

        public int startRow() throws IndexOutOfBoundsException {
            int row = 3;
            String B = xSpreadsheet.getCellByPosition(1, row).getFormula();
            while ( B != null && ! B.isEmpty()){
                row++;
                 B = xSpreadsheet.getCellByPosition(1, row).getFormula();
            }
            return row;
        }
    }


}
