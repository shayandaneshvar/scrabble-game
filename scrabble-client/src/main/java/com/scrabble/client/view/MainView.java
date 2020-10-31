package com.scrabble.client.view;

import com.scrabble.client.controller.Command;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainView {
    private Group root;
    private Stage primaryStage;
    private Scene scene;
    private Command<String> startMultiCommand;

    public MainView(Group root, Stage primaryStage, Scene scene) {
        this.root = root;
        this.primaryStage = primaryStage;
        this.scene = scene;
        showMainMenu();
    }

    public void showMainMenu() {
        root.getChildren().clear();
        VBox vBox = new VBox();
        scene.setFill(Color.web("#260c1a"));
        root.getChildren().addAll(vBox);
        Title title = new Title("SCRABBLE");
        MenuItem singlePlayer = new MenuItem("Single-player", 500, 170);
        MenuItem multiPlayer = new MenuItem("Multi-player", 500, 170);
        MenuItem exit = new MenuItem("Exit", 500, 180);
        MenuBox menuBox = new MenuBox(singlePlayer, multiPlayer, exit);
        vBox.getChildren().addAll(title, menuBox);
        vBox.setTranslateX(252d);
        vBox.setTranslateY(64d);
        vBox.setSpacing(68d);
        exit.setOnMouseClicked(event -> System.exit(0));
        multiPlayer.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Start");
            Group root1 = new Group();
            Scene scene1 = new Scene(root1, 294, 130);
            scene1.setFill(Color.web("#260c1a"));
            stage.setScene(scene1);
            stage.show();
            TextField textField = new TextField();
            textField.setPadding(new Insets(5, 10, 5, 10));
            textField.setTranslateX(20);
            textField.setTranslateY(10);
            textField.setPromptText("Name");
            MenuItem menuItem = new MenuItem("Start", 200, 50);
            menuItem.setPadding(new Insets(5, 30, 5, 10));
            menuItem.setTranslateX(30);
            menuItem.setTranslateY(20);
            VBox vBox1 = new VBox();
            vBox1.setSpacing(4);
            vBox1.setPadding(new Insets(8d));
            root1.getChildren().addAll(vBox1);
            vBox1.getChildren().addAll(textField, menuItem);
            menuItem.setOnMouseClicked(z -> {
                stage.close();
                startMultiCommand.execute(textField.getText());
            });
        });
    }

    public void setStartMultiCommand(Command<String> startCommand) {
        this.startMultiCommand = startCommand;
    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(500, 100);
            bg.setStroke(Color.web("#c5d86d"));
            bg.setStrokeWidth(3);
            bg.setFill(Color.web("#f05d23"));
            Text text = new Text(name);
            text.setFill(Color.web("#f7f7f2"));
            text.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                    64));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }

    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());
            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(502);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }


    public static class MenuItem extends StackPane {
        public MenuItem(String name, int width, int height) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT),
                    new Stop(0.1, Color.web("#f05d23")),
                    new Stop(0.9, Color.web("#f05d23")),
                    new Stop(1, Color.TRANSPARENT));
            Rectangle bg = new Rectangle(width, height);
            bg.setFill(gradient);
            bg.setOpacity(0.9);
            Text text = new Text(name);
            text.setFill(Color.web("#f7f7f2"));
            text.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                    28));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                text.setFill(Color.web("#c5d86d"));
                bg.setFill(Color.web("#f05d23"));
            });
            setOnMouseExited(event -> {
                text.setFill(Color.web("#f7f7f2"));
                bg.setFill(gradient);
            });
            setOnMousePressed(event -> bg.setFill(Color.web("#f7f7f2")));
            setOnMouseReleased(event -> bg.setFill(gradient));
        }
    }
}
