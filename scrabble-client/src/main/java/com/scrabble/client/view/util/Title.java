package com.scrabble.client.view.util;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends StackPane {
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
