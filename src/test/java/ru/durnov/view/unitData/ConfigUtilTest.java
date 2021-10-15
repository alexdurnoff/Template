package ru.durnov.view.unitData;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ConfigUtilTest {
    private final Path path = Path.of("testFiles/testConfig.txt");

    @Test
    void testBreakers() throws IOException {
        ConfigUtil configUtil = new ConfigUtil("breakers", path);
        ObservableList<String> items = configUtil.items();
        assertEquals(items.size(), 4);
        assertEquals(items.get(0), "ABB S201");
        assertEquals(items.get(1), "ABB S203");
        assertEquals(items.get(2), "ABB XT2N160");
        assertEquals(items.get(3), "ABB XT4N250");

    }

}