package com.scrabble.client.view;

import com.scrabble.client.model.NetworkEnabledGame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MultiPlayerGameView implements GameView<NetworkEnabledGame>,
        Observer<NetworkEnabledGame> {
    private Stage primaryStage;
    private Group root;
    private Scene scene;

    public MultiPlayerGameView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new Group();
        scene = new Scene(root, 1000, 800, false,
                SceneAntialiasing.BALANCED);
    }

    @Override
    public void drawGameBoard(NetworkEnabledGame game) {
        root.getChildren().clear();
        BorderPane pane = new BorderPane();
        root.getChildren().add(pane);
        GridPane gridPane = new GridPane();
        pane.setLeft(gridPane);
        // TODO: 10/31/2020  


    }


    @Override
    public void update(NetworkEnabledGame game) {
        drawGameBoard(game);
    }
}
