package ru.durnov.view;


import ru.durnov.ControlRemoteOffice;

public class TemplateSpreadSheet implements SpreadSheet {

    @Override
    public Object[][] data() {
        return ControlRemoteOffice.getSourceData();
    }
}
