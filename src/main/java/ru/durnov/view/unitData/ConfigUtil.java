package ru.durnov.view.unitData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigUtil implements Config{
    private final String unitName;
    private final Path path;


    public ConfigUtil(String unitName, Path path) {
        this.unitName = unitName;
        this.path = path;
    }


    @Override
    public ObservableList<String> items() throws IOException {
        ObservableList<String> items = FXCollections.observableArrayList();
        List<String> strings = Files.readAllLines(path);
        strings.forEach(s -> {
            if (s.contains(unitName)){
                String[] split = s.substring(s.indexOf(':') + 1).split(",");
                for (String s1 : split) {
                    items.add(s1.trim());
                }
            }
        });
        return items;
    }
}
