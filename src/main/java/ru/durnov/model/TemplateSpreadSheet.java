package ru.durnov.model;

import remoteoffice.ControlRemoteOffice;

public class TemplateSpreadSheet implements SpreadSheet {

    @Override
    public Object[][] data() {
        return ControlRemoteOffice.getSourceData();
    }
}
