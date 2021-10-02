package ru.durnov.view.unitData.breakers;

import java.util.HashMap;
import java.util.Map;

public class InstantCurrent {
    private final String breakerType;
    private final Map<String, Double> currentMap = new HashMap<>();

    public InstantCurrent(String breakerType) {
        this.breakerType = breakerType;
        this.currentMap.put("C", 10.);
        this.currentMap.put("B", 5.);
        this.currentMap.put("D", 20.);
    }

    public double value(){
        if (currentMap.containsKey(breakerType)) return currentMap.get(breakerType);
        return 1.;
    }
}
