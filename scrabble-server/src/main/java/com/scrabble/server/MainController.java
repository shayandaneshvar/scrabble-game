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
import java.util.stream.Collectors;

public class MainController {
    private ServerSocket serverSocket;
    private final List<PlayerInfo> playerInfos = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final List<PlayerHandler> playerList = new ArrayList<>();
    private Boolean gameStarted = false;

    public synchronized void handleDisconnection(SocketException e, PlayerInfo playerInfo) {
        System.out.println(playerInfo.getName() + " Disconnected!");
        synchronized (playerInfo) {
            playerInfos.remove(playerInfo);
            synchronized (atomicBoolean) {
                if (playerInfo.getAdmin()) {
                    atomicBoolean.set(false);
                }
            }
        }
        synchronized (playerList) {
            PlayerHandler playerHandler = playerList.stream()
                    .filter(z -> z.getPlayerInfo().equals(playerInfo))
                    .findFirst().orElse(null);
            if (playerHandler == null) {
                return;
            }
            playerList.remove(playerHandler);
        }
    }

    public MainController(Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void startGame() {
        gameStarted = true;
        updateEachPlayer();
        CircularQueue<Player> playerQueue = new CircularQueue<>(playerList.size());
        playerList.forEach(z -> playerQueue.push(new Player(z.getPlayerInfo(), z.getInputStream(), z.getOutputStream())));
        GameManager manager = new GameManager(playerQueue);
        executorService.execute(manager);
    }

    public void init() {
        System.out.println("Starting Server...");
        Socket socket;
        while (!gameStarted) {
            if (playerInfos.size() >= 4) {
                continue;
            }
            try {
                System.out.println("Accepting Clients ...");
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress() + " is Connecting...");
                PlayerInfo playerInfo = new PlayerInfo("Undefined");
                PlayerHandler handler = new PlayerHandler(playerInfo, socket, atomicBoolean);
                handler.setUpdateCommand(this::updateEachPlayer);
                handler.setDisconnectionCommand(this::handleDisconnection);
                handler.setStartGameCommand(this::startGame);
                executorService.execute(handler);
                synchronized (playerList) {
                    playerList.add(handler);
                }
                playerInfo.setAddress(socket.getInetAddress());
                playerInfos.add(playerInfo);
                Thread.sleep(100);
                updateEachPlayer();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.out.println("An Error Occurred...!");
            }
        }
    }

    private synchronized void updateEachPlayer() {
        var update = playerList.stream()
                .map(PlayerHandler::getPlayerInfo)
                .collect(Collectors.toList());
        playerList.forEach(z -> z.update(update));
    }
}
