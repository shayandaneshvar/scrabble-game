package com.scrabble.client.controller;

import com.scrabble.client.model.HumanPlayer;
import com.scrabble.client.view.MainView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;

public class MainController extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, false,
                SceneAntialiasing.BALANCED);
        primaryStage.setScene(scene);
        Circle circle = new Circle(20, 20, 20);
        root.getChildren().addAll(circle);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(new File(
                "scrabble-client/src/main/resources/images/icon.png")
                .getAbsoluteFile().toURI().toURL().toString()));
        primaryStage.setTitle("Scrabble");
        primaryStage.show();
        MainView mainView = new MainView(root, primaryStage, scene);
        mainView.setStartMultiCommand(this::startMultiPlayerGame);
        mainView.showMainMenu();
    }

    public void startMultiPlayerGame(String name) {
        HumanPlayer humanPlayer = new HumanPlayer(name);
        NetworkEnabledGameController controller =
                new NetworkEnabledGameController(humanPlayer, primaryStage);
        controller.init();
    }

    public static void init(String... args) {
        launch(args);
    }
}
