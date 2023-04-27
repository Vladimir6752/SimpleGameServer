package com.example.server.gamelogic.queues;

import com.example.server.gamelogic.models.Game;
import com.example.server.gamelogic.models.Player;

import java.util.List;

public class PvpGame extends Game {
    public PvpGame(List<Player> playerList) {
        super(playerList);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean isContinue() {
        return false;
    }
}
