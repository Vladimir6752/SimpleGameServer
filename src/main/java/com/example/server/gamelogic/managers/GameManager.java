package com.example.server.gamelogic.managers;

import com.example.server.gamelogic.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private final List<Game> games = new ArrayList<>();

    /**
     * @return Singleton
     */
    public static GameManager getInstance() {
        return instance;
    }

    private GameManager() {}

    /**
     * Observer
     */
    public void notifyGames() {
        for (Game game : games) {
            if (game.isContinue())
                game.tick();
        }
    }

    public void addNewGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }
}
