package ru.durnov.view.unitData.cables;

public class FazaValue {
    private String value = "с";

    public String value(){
        return nextValue();
    }

    private String nextValue() {
        if (value.equals("а")){
            value = "б";
        } else if (value.equals("б")){
            value = "с";
        } else {
            value = "а";
        }
        return value;
    }

}
