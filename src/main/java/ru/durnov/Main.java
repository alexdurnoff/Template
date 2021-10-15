package ru.durnov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Objects;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/RootPanel.fxml"));
        AnchorPane rootPanel = (AnchorPane) loader.load();
        TemplateController templateController = loader.getController();
        templateController.setStage(primaryStage);
        Scene scene = new Scene(rootPanel, 1300, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
