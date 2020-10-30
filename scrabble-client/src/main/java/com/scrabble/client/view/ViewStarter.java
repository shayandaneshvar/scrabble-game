package com.scrabble.client.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ViewStarter extends Application {

    public static void start(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // delete these and write your own logic
        Pane root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Circle circle = new Circle(20, 20, 20);
        root.getChildren().addAll(circle);
        primaryStage.show();
    }
}
