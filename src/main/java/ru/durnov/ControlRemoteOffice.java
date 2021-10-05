package ru.durnov;

import com.sun.star.awt.*;
import com.sun.star.beans.*;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.container.XNameAccess;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XFrame;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XCellRangeData;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.table.XCell;
import com.sun.star.table.XCellRange;
import com.sun.star.table.XColumnRowRange;
import com.sun.star.table.XTableRows;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import ru.durnov.ui.ExceptionWindow;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class ControlRemoteOffice {

    public static XFrame getCurrentFrame() throws Exception, BootstrapException {
        XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        Object oDesktop = xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop",
                xComponentContext);
        XDesktop xDesktop = UnoRuntime.queryInterface(XDesktop.class, oDesktop);
        XFrame xFrame = xDesktop.getCurrentFrame();
        return xFrame;
    }

    public static XMultiComponentFactory getXMultiComponentFactory() throws BootstrapException {
        XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        return xMultiComponentFactory;
    }


    public static XSpreadsheet getCurrentSpreadsheet(String spreadsheetname)
            throws Exception, BootstrapException {
        //Возвращает лист электронной таблицы по имени.
        /*XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        Object oDesktop = xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop",
                xComponentContext);
        XDesktop xDesktop = UnoRuntime.queryInterface(XDesktop.class, oDesktop);
        XComponent model = xDesktop.getCurrentComponent();*/
        XComponent model = getCurrentComponent();
        XSpreadsheetDocument xSpreadsheetDocument = UnoRuntime.queryInterface(XSpreadsheetDocument.class,model);
        XSpreadsheets xSpreadsheets = xSpreadsheetDocument.getSheets();
        XNameAccess xNameAccess = UnoRuntime.queryInterface(XNameAccess.class, xSpreadsheets);
        return UnoRuntime.queryInterface(XSpreadsheet.class, xNameAccess.getByName(spreadsheetname));
    }

    public static XComponent getCurrentComponent() throws Exception, BootstrapException {
        XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        Object oDesktop = xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop",
                xComponentContext);
        XDesktop xDesktop = UnoRuntime.queryInterface(XDesktop.class, oDesktop);
        XComponent model = xDesktop.getCurrentComponent();
        return model;
    }

    public static void writedatatocell(XCell cell, Object data){
        //Записывает данные в ячейку. Тип данных может приходить разный. Пишет либо строку, либо значение
        if (data.getClass() == String.class) {
            cell.setFormula((String) data);
        }
        else {
            cell.setValue((int) data);
        }
    }

    public static void copyCellRangeDataFromHelpFile(int rowNumber, XSpreadsheet isolationSheet,
                                                     XSpreadsheet helpSpreadSheet) throws IndexOutOfBoundsException {
        XCellRange isolationRange = isolationSheet.getCellRangeByPosition(0,19,14,(rowNumber+18));
        XCellRange helpRange = helpSpreadSheet.getCellRangeByPosition(1,1,15,rowNumber);
        XCellRangeData helpData = UnoRuntime.queryInterface(XCellRangeData.class, helpRange);
        XCellRangeData isolationData = UnoRuntime.queryInterface(XCellRangeData.class, isolationRange);
        isolationData.setDataArray(helpData.getDataArray());

    }

    public static void insertRow(int startRow, int rowNumber, XSpreadsheet xSpreadsheet) throws IndexOutOfBoundsException {
        try {
            XColumnRowRange xColumnRowRange = UnoRuntime.queryInterface(XColumnRowRange.class, xSpreadsheet);
            XTableRows xTableRows = xColumnRowRange.getRows();
            xTableRows.insertByIndex(startRow, rowNumber);
        } catch (java.lang.Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Property[] getCellProperties(XCell xCell) throws BootstrapException, Exception {
        XPropertySet xPropertySet = UnoRuntime.queryInterface(XPropertySet.class, xCell);
        XPropertySetInfo xPropertySetInfo = xPropertySet.getPropertySetInfo();
        Property[] properties = xPropertySetInfo.getProperties();
        for (int i = 0; i < properties.length; i++){
            System.out.println(properties[i].Name);
        }
        return properties;

    }

    public static  void setCellStyle(XCell xCell, String styleName)
            throws WrappedTargetException, UnknownPropertyException, PropertyVetoException {
        XPropertySet xPropertySet = UnoRuntime.queryInterface(XPropertySet.class, xCell);
        XPropertySetInfo xPropertySetInfo = xPropertySet.getPropertySetInfo();
        Property style = xPropertySetInfo.getPropertyByName("CellStyle");
        xPropertySet.setPropertyValue(style.Name, styleName);
    }

    public static  String getCellStyle(XCell xCell) throws WrappedTargetException,
            UnknownPropertyException, PropertyVetoException {
        XPropertySet xPropertySet = UnoRuntime.queryInterface(XPropertySet.class, xCell);
        XPropertySetInfo xPropertySetInfo = xPropertySet.getPropertySetInfo();
        Property style = xPropertySetInfo.getPropertyByName("CellStyle");
        Object property = xPropertySet.getPropertyValue("CellStyle");
        return property.toString();

    }

    public static int getFastNumberOfSourceLines(XSpreadsheet sourceSheet) throws IndexOutOfBoundsException {
        int step = 1000;
        int firstnumber = 4;
        int lastnumber = firstnumber + step;
        String B;
        while (step > 1){
            System.out.println("firstnumber = " + firstnumber);
            System.out.println("lastnumber = " + lastnumber);
            B = sourceSheet.getCellByPosition(1, lastnumber).getFormula();
            while (B.equals("")){
                if (step >1) {step /= 2;}
                System.out.println("step =" + step);
                lastnumber -= step;
                System.out.println("lastnumber = " + lastnumber);
                B = sourceSheet.getCellByPosition(1, lastnumber).getFormula();
                System.out.println("B = " + B);
            }
            firstnumber = lastnumber;
            lastnumber = firstnumber + step;
        }
        step = 1;
        B = sourceSheet.getCellByPosition(1, lastnumber).getFormula();
        if (B.equals("")){
            while (B.equals("")){
                lastnumber -= step;
                B = sourceSheet.getCellByPosition(1, lastnumber).getFormula();
            }

        } else {
            while (!(B.equals(""))){
                lastnumber += step;
                B = sourceSheet.getCellByPosition(1, lastnumber).getFormula();
            }
            lastnumber -= 1;

        }
        return lastnumber;
    }

    public static int getNumberOfSourceLines(XSpreadsheet sourceSheet) throws IndexOutOfBoundsException {
        int number = 3;
        String B = sourceSheet.getCellByPosition(1, number).getFormula();
        while (!(B.equals(""))){
            number += 1;
            B = sourceSheet.getCellByPosition(1, number).getFormula();
        }
        return number-1;
    }

    public static int getStartRow(String sheetName){
        int startRow = 0;
        switch (sheetName){
            case "Изоляция":
                startRow = 19;
                break;
            case "УЗО":
                startRow = 14;
                break;
            case "Петля":
            case "Автомат":
                startRow = 21;
                break;
        }
        return startRow;
    }

    public static int getEndColumn(String sheetName){
        int endColumn = 0;
        switch (sheetName){
            case "Изоляция":
            case "УЗО":
                endColumn = 14;
                break;
            case "Петля":
                endColumn = 13;
                break;
            case "Автомат":
                endColumn = 16;
                break;
        }
        return endColumn;
    }

    public static int getNumberOfProtokolLines(String sheetName)
            throws Exception, BootstrapException {
        XSpreadsheet protokolSheet = getCurrentSpreadsheet(sheetName);
        int number = 0;
        switch (sheetName){
            case "Изоляция":
                number = 19;
                break;
            case "УЗО":
                number = 12;
                break;
            case "Петля":
            case "Автомат":
                number = 21;
                break;
        }
        String B = protokolSheet.getCellByPosition(1, number).getFormula();
        while (!(B.equals(""))){
            number ++;
            B = protokolSheet.getCellByPosition(1, number).getFormula();
        }
        return number - 1;
    }

    //Возвращает массив данных для копирования в odt-файл
    public static Object[][] getSourceProtokolData(String sheetName) throws Exception, BootstrapException {
        XSpreadsheet sheet = getCurrentSpreadsheet(sheetName);
        int startRow = 0;
        int endColumn = 0;
        switch (sheetName){
            case "Изоляция":
                startRow = 19;
                endColumn = 14;
                break;
            case "УЗО":
                startRow = 14;
                endColumn = 14;
                break;
            case "Петля":
                startRow = 21;
                endColumn = 13;
                break;
            case "Автомат":
                startRow = 21;
                endColumn = 16;
                break;
        }
        XCellRange xCellRange = sheet.getCellRangeByPosition(0, startRow,
                endColumn, getNumberOfProtokolLines(sheetName));
        XCellRangeData xCellRangeData = UnoRuntime.queryInterface(XCellRangeData.class, xCellRange);
        Object[][] sourceIsolationData = xCellRangeData.getDataArray();
        return sourceIsolationData;
    }

    public static XDialog getFileOpenDialog() throws BootstrapException, Exception {
        XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = ControlRemoteOffice.getXMultiComponentFactory();
        Object DialogProvider = xMultiComponentFactory.createInstanceWithContext
                ("com.sun.star.awt.DialogProvider", xComponentContext);
        XDialogProvider xDialogProvider = UnoRuntime.queryInterface(XDialogProvider.class, DialogProvider);
        XDialog xDialog = xDialogProvider.createDialog("vnd.sun.star.script:Standard.fileOpen?location=application");
        return xDialog;
    }

    public static String getExportFilePath() throws BootstrapException, Exception {
        XComponentContext xComponentContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
        XMultiComponentFactory xMultiComponentFactory = ControlRemoteOffice.getXMultiComponentFactory();
        Object DialogProvider = xMultiComponentFactory.createInstanceWithContext
                ("com.sun.star.awt.DialogProvider", xComponentContext);
        XDialogProvider xDialogProvider = UnoRuntime.queryInterface(XDialogProvider.class, DialogProvider);
        XDialog xDialog = xDialogProvider.createDialog("vnd.sun.star.script:Standard.fileOpen?location=application");
        xDialog.setTitle("Выбираем файл для экспорта данных протокола");
        xDialog.execute();
        XControlContainer xControlContainer = UnoRuntime.queryInterface(XControlContainer.class, xDialog);
        XControl xControl = xControlContainer.getControl("FileControl1");
        XTextComponent xTextComponent = UnoRuntime.queryInterface(XTextComponent.class, xControl);
        return xTextComponent.getText();
    }

    public static void copyDataArrayToSpreadsheet(Object[][] sourceData, int startColumn,
                                                  int startRow, int endColumn, XSpreadsheet xSpreadsheet){
        try {
            XCellRange xCellRange = xSpreadsheet.getCellRangeByPosition(startColumn, startRow, endColumn,
                    sourceData.length + startRow -1);
            XCellRangeData xCellRangeData = UnoRuntime.queryInterface(XCellRangeData.class, xCellRange);
            xCellRangeData.setDataArray(sourceData);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            ExceptionWindow exceptionWindow =
                    new ExceptionWindow("Ошибка при копировании массива в данные листа" + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(sourceData.length);
            System.out.println(startRow);
            System.out.println(endColumn);
            new ExceptionWindow("RuntimeException при копировании массива в данные листа" + e.getMessage());
        }
    }

    public static Object[][] getSourceData() {
        Object[][] sourceData = new Object[][]{};
        try {
            XSpreadsheet sourceSheet = getCurrentSpreadsheet("Черновик");
            int numberOfSourceLines = getNumberOfSourceLines(sourceSheet);
            XCellRange xCellRange = sourceSheet.getCellRangeByPosition(0, 3, 22, numberOfSourceLines);
            XCellRangeData xCellRangeData = UnoRuntime.queryInterface(XCellRangeData.class, xCellRange);
            sourceData = xCellRangeData.getDataArray();
            return sourceData;
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionWindow exceptionWindow = new ExceptionWindow("Ошибка при создании массива исходных данных " +
                    e.getMessage());
        } catch (BootstrapException e) {
            e.printStackTrace();
            ExceptionWindow exceptionWindow = new ExceptionWindow("BootstrapException при создании массива исходных данных");
        }
        return sourceData;
    }

    /**
     * Метод "заливавет" пустые ячейки массива данных data пустыми строками, чтобы не было null в массиве.
     */
    public static void fillDataArray(Object[][] data){
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < 6; j++){
                if (data[i][j] == null) data[i][j] = "";
            }
        }
    }
    
    /**
     * This method return Path to current component office
     * @return
     * @throws Exception
     * @throws BootstrapException
     */
    public static Path geCurrentFilePath() throws Exception, BootstrapException {
    	String url = ControlRemoteOffice.getCurrentFrame().getController().getModel().getURL();
    	int firstIndex = url.indexOf("/");
		return Paths.get(url.substring(firstIndex));
    }
    
    public static Path getCurrentDirectoryPath() throws Exception, BootstrapException {
    	String url = geCurrentFilePath().getParent().toString();
    	int firstIndex = url.indexOf("/");
    	return Paths.get(url.substring(firstIndex));
    }


    public static Set<String> getPanelHeaders() throws BootstrapException, Exception {
        Set<String> headers = new HashSet<>();
        XSpreadsheet template = getCurrentSpreadsheet("Черновик");
        int rowNumber = 3;
        String B = template.getCellByPosition(1, rowNumber).getFormula();
        while (!B.isEmpty()){
            String nextA = template.getCellByPosition(0, rowNumber + 1).getFormula();
            String A = template.getCellByPosition(0, rowNumber).getFormula();
            System.out.println("A = " + A + " nextA = " + nextA + " B = " + B + " A.isEmpty " + A.isEmpty() + " condition " + (A.isEmpty() && !nextA.isEmpty()));
            if (A.isEmpty() && !nextA.isEmpty()){
                headers.add(B);
            }
            rowNumber++;
            B = template.getCellByPosition(1, rowNumber).getFormula();
        }
        return headers;
    }

    /**
     * Проверка, должна ли быть записана строка.
     * @param dataSource - массив данных для записи.
     * @param i - индекс строки.
     * @param panelHeaders - сет заголовоков панелей и щитов.
     * @return - false, если строка заголовок, а следующая строка - либо тоже заголовок, либо наименование этажа/помещения.
     */
    public static boolean rowMustBeWritten(Object[][] dataSource, int i, Set<String> panelHeaders) {
        if (i == dataSource.length - 1) return true;
        Object[] data = dataSource[i];
        String B = String.valueOf(data[i]);
        if (!panelHeaders.contains(B)){
            return true;
        } else {
            Object[] nextData = dataSource[i+1];
            String nextA = String.valueOf(nextData[0]);
            return !nextA.isEmpty() && !nextA.contains("Полюс");
        }
    }
}
