package com.scrabble.server;

public class ServerMain {
    public static void main(String[] args) {
        MainController mainController = null;
        try {
            mainController = new MainController(43210);
            mainController.init();
        } catch (Exception e) {
            throw new RuntimeException("Server Start Failed....", e);
        }
    }
}
