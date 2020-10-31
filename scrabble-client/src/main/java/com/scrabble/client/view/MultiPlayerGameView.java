package com.scrabble.client.view;

import com.scrabble.client.model.CellContent;
import com.scrabble.client.model.NetworkEnabledGame;
import com.scrabble.client.view.util.Title;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MultiPlayerGameView implements GameView<NetworkEnabledGame>,
        Observer<NetworkEnabledGame> {
    private Stage primaryStage;
    private Group root;
    private Scene scene;

    public MultiPlayerGameView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new Group();
        scene = new Scene(root, 1150, 900, false,
                SceneAntialiasing.BALANCED);
        scene.setFill(Color.web("#260c1a"));
        primaryStage.setScene(scene);
    }

    @Override
    public void drawGameBoard(NetworkEnabledGame game) {
        root.getChildren().clear();
        Pane pane = new Pane();
        root.getChildren().add(pane);
        GridPane gridPane = new GridPane();
        pane.getChildren().add(gridPane);
        Title title = new Title("Scrabble");
        title.setScaleX(0.81);
        title.setScaleY(0.81);
        title.setTranslateX(320);
        pane.getChildren().add(title);
        gridPane.setTranslateY(100);
        gridPane.setTranslateX(4);
        gridPane.setHgap(2d);
        gridPane.setVgap(1.5d);
        gridPane.setStyle("-fx-background-color: #260c1a;-fx-border-color: #f05d23;"
                + "-fx-border-style:SOLID;-fx-padding: 6px;-fx-border-radius: "
                + "2%;-fx-background-radius:2% ; -fx-border-width: 2px");
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (game.getBoard().getCell(i, j).equals(CellContent.EMPTY)) {
                    gridPane.add(createCell(" X ", 28, "Wheat", "Wheat")
                            , i, j, 1, 1);
                    continue;
                }
                gridPane.add(createCell(" " + game.getBoard().getCell(i, j)
                                .toString() + " ", 28, "Wheat", "#f7f7f2")
                        , i, j, 1, 1);
            }
        }
        // TODO: 10/31/2020

    }

    public static Label createCell(String string, int size, String bColor,
                                   String fColor) {
        Label label = new Label(string);
        label.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                FontPosture.REGULAR, size));
        label.setStyle("-fx-background-color:" + bColor + ";-fx-padding: 10px" +
                ";-fx-background-radius: 7%;-fx-text-fill:" + fColor);
        label.setAlignment(Pos.CENTER);
        return label;
    }


    @Override
    public void update(NetworkEnabledGame game) {
        drawGameBoard(game);
    }
}
