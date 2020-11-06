package com.scrabble.server;

import com.scrabble.server.dto.PlayerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainController {
    private ServerSocket serverSocket;
    private volatile List<PlayerInfo> playerInfos = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void handleDisconnection(SocketException e, PlayerInfo playerInfo) {
        playerInfos.remove(playerInfo);
    }

    public MainController(Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void init() throws IOException, ClassNotFoundException {
        System.out.println("Starting Server...");
        Socket socket;
        List<PlayerHandler> playerList = new ArrayList<>();
        while (true) {
            try {
                System.out.println("Accepting Clients ...");
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress() + " is Connecting...");
                PlayerInfo playerInfo = new PlayerInfo("Undefined");
                PlayerHandler handler = new PlayerHandler(playerInfo, socket, atomicBoolean, playerInfos);
                handler.setDisconnectionCommand(this::handleDisconnection);
                executorService.execute(handler);
                playerList.add(handler);
                playerInfo.setAddress(socket.getInetAddress());
                playerInfos.add(playerInfo);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An Error Occurred...!");
            }

        }
    }
}
