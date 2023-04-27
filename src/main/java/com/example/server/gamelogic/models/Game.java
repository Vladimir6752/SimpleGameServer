package com.example.server.gamelogic.models;

import java.util.List;

public abstract class Game {
    protected final List<Player> playerList;

    protected Game(List<Player> playerList) {
        this.playerList = playerList;
    }

    public abstract void tick();

    public abstract boolean isContinue();
}
