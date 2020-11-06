package com.scrabble.client.view;

import com.scrabble.client.controller.Command;
import com.scrabble.client.view.util.Title;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import static com.scrabble.client.view.util.Menus.MenuBox;
import static com.scrabble.client.view.util.Menus.MenuItem;

public class MainView {
    private Group root;
    private Stage primaryStage;
    private Scene scene;
    private Command<Pair<String, String>> startMultiCommand;

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
        vBox.setTranslateX(152d);
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
            stage.setResizable(false);
            Group root1 = new Group();
            Scene scene1 = new Scene(root1, 294, 160);
            scene1.setFill(Color.web("#260c1a"));
            stage.setScene(scene1);
            stage.show();
            TextField textField = new TextField();
            textField.setPadding(new Insets(5, 10, 5, 10));
            textField.setTranslateX(20);
            textField.setTranslateY(10);
            textField.setPromptText("Name");
            TextField host = new TextField();
            host.setPadding(new Insets(5, 10, 5, 10));
            host.setTranslateX(20);
            host.setTranslateY(10);
            host.setPromptText("Host Address: 127.0.0.1");
            MenuItem menuItem = new MenuItem("Start", 200, 50);
            menuItem.setPadding(new Insets(5, 30, 5, 10));
            menuItem.setTranslateX(30);
            menuItem.setTranslateY(20);
            VBox vBox1 = new VBox();
            vBox1.setSpacing(4);
            vBox1.setPadding(new Insets(8d));
            root1.getChildren().addAll(vBox1);
            vBox1.getChildren().addAll(textField, host, menuItem);
            menuItem.setOnMouseClicked(z -> {
                stage.close();
                startMultiCommand.execute(new Pair<>(textField.getText(), host.getText()));
            });
        });
    }

    public void setStartMultiCommand(Command<Pair<String, String>> startCommand) {
        this.startMultiCommand = startCommand;
    }

}
