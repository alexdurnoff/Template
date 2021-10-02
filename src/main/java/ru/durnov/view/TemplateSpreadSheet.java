package ru.durnov.view;

import remoteoffice.ControlRemoteOffice;

public class TemplateSpreadSheet implements SpreadSheet {

    @Override
    public Object[][] data() {
        return ControlRemoteOffice.getSourceData();
    }
}
