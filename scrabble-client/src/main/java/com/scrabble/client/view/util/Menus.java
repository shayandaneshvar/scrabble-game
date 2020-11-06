package com.scrabble.client.view.util;

import javafx.geometry.Pos;
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

public final class Menus {
    private Menus() {
    }

    public static class MenuBox extends VBox {
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
