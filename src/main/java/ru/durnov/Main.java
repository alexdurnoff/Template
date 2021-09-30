package ru.durnov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.durnov.model.RootPanel;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RootPanel rootPanel = (RootPanel) FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/RootPanel.fxml"))
        );
        Scene scene = new Scene(rootPanel, 1300, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
