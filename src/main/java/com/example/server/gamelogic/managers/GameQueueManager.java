package com.example.server.gamelogic.managers;

import com.example.server.gamelogic.models.GameQueue;
import com.example.server.gamelogic.queues.PvpGameQueue;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;
import java.util.List;

public class GameQueueManager {
    private static final GameQueueManager instance = new GameQueueManager();
    private final List<GameQueue> gameQueues = Arrays.asList(
            new PvpGameQueue()
    );

    private GameQueueManager() {}

    /**
     * @return Singleton
     */
    public static GameQueueManager getInstance() {
        return instance;
    }

    public void tryToAddPlayerInQueue(String playerNickname, String gameMode, WebSocketSession session) {
        GameQueue foundedGameQueue = gameQueues
                .stream()
                .filter(gameQueue -> gameQueue.getModeName().equals(gameMode))
                .findFirst()
                .orElse(null);

        if (foundedGameQueue != null)
            foundedGameQueue.addPlayerInQueue(playerNickname, session);
        else
            System.err.printf("Player %s tried to enter a non-existent queue: %s%n", playerNickname, gameMode);
    }

    public void tryToRemovePlayerFromQueue(String playerNickname) {
        gameQueues.forEach(gameQueue -> gameQueue.removePlayerFromQueue(playerNickname));
    }
}
