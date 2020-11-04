package com.scrabble.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainController {
    private ServerSocket serverSocket;
    private HashMap<Socket, PlayerInfo> playerHashMap = new HashMap<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public MainController(Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void init() throws IOException, ClassNotFoundException {
        Socket socket;
        List<PlayerHandler> playerList = new ArrayList<>();
        while (true) {
            try {
                socket = serverSocket.accept();
                PlayerInfo playerInfo = new PlayerInfo("Undefined");
                PlayerHandler handler = new PlayerHandler(playerInfo, socket, atomicBoolean,playerHashMap);
                executorService.execute(handler);
                playerList.add(handler);
                playerHashMap.put(socket, playerInfo);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }


        }

    }
}
