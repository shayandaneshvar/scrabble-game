package com.scrabble.client.view;

import com.scrabble.client.controller.Command;
import com.scrabble.client.model.CellContent;
import com.scrabble.client.model.NetworkEnabledGame;
import com.scrabble.client.view.util.Title;
import com.scrabble.server.dto.PlayerInfo;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.stream.IntStream;

public class MultiPlayerGameView implements GameView<NetworkEnabledGame>,
        Observer<NetworkEnabledGame> {
    private Stage primaryStage;
    private Group root;
    private Scene scene;
    private Command<Boolean> startGameCommand;

    public MultiPlayerGameView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new Group();
        scene = new Scene(root, 1024, 900, false,
                SceneAntialiasing.BALANCED);
        scene.setFill(Color.web("#260c1a"));
        primaryStage.setScene(scene);
    }

    @Override
    public void drawGameBoard(NetworkEnabledGame game) {
        root.getChildren().clear();
        Pane pane = new Pane();
        root.getChildren().add(pane);
        Title title = new Title("Scrabble");
        GridPane gridPane = new GridPane();
        pane.getChildren().add(gridPane);
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
        VBox vBox = new VBox();
        pane.getChildren().add(vBox);
        vBox.setStyle("-fx-background-color: #260c1a;-fx-border-color: #f05d23;"
                + "-fx-border-style:SOLID;-fx-padding: 6px;-fx-border-radius: "
                + "2%;-fx-background-radius:2% ; -fx-border-width: 2px");
        vBox.setTranslateX(780);
        vBox.setTranslateY(100);

        Iterator<PlayerInfo> playerInfoIterator = game.getOtherPlayers().iterator();
        while (playerInfoIterator.hasNext()) {
            PlayerInfo z = playerInfoIterator.next();
            VBox vBox1 = new VBox();
            vBox1.setStyle("-fx-background-color: #260c1a;-fx-border-color: #f7f7f2;"
                    + "-fx-border-style:SOLID;-fx-padding: 6px;-fx-border-radius: "
                    + "2%;-fx-background-radius:2% ; -fx-border-width: 2px");
            Label name = new Label();
            String rawName = z.getName();
            int length = 10 - z.getName().length();
            StringBuilder offset = new StringBuilder();
            IntStream.range(0, length).forEach(y -> offset.append(" "));
            name.setText(offset.substring(length / 2) + rawName.concat(offset.substring(length / 2)));

            name.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                    FontPosture.REGULAR, 32));
            name.setStyle("-fx-background-color: transparent;-fx-padding: 16px" +
                    ";-fx-background-radius: 7%;-fx-text-fill: #c5d86d");
            name.setAlignment(Pos.CENTER);
            vBox1.getChildren().add(name);
            if (z.getName().equals(game.getPlayer().getName()) && !game.getGameStarted()) {
                Button button = new Button("Start Game");
                if (!z.getAdmin()) {
                    button.setDisable(true);
                }
                button.setOnMouseClicked(e -> {
                    startGameCommand.execute(true);
                    button.setDisable(true);
                    z.setAdmin(false);
                });
                button.setStyle("-fx-background-color: #f05d23; -fx-fill: #f7f7f2; ");
                vBox1.getChildren().add(button);
            } else {
                Label ip = new Label();
                ip.setText(z.getAddress().getHostAddress());
                ip.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                        FontPosture.REGULAR, 18));
                ip.setStyle("-fx-background-color: transparent;-fx-padding: 16px" +
                        ";-fx-background-radius: 7%;-fx-text-fill: #f7f7f2");
                ip.setAlignment(Pos.CENTER);
                vBox1.getChildren().add(ip);
            }
            vBox.getChildren().add(vBox1);
        }

        if (game.getGameStarted()) {
            FlowPane flow = new FlowPane();
            flow.setPrefWrapLength(210);
            flow.setStyle("-fx-background-color: #260c1a;-fx-border-color: #f05d23;"
                    + "-fx-border-style:SOLID;-fx-padding: 6px;-fx-border-radius: "
                    + "2%;-fx-background-radius:2% ; -fx-border-width: 2px");
            game.getPlayer().getCharacters().forEach(z -> flow.getChildren().add
                    (createCell(" " + z.toString() + " ", 24, "Wheat", "#f05d23")));
            pane.getChildren().add(flow);
            flow.setTranslateX(780);
            flow.setTranslateY(684);
        }
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

    public void setGameStartCommand(Command<Boolean> startGame) {
        startGameCommand = startGame;
    }
}
