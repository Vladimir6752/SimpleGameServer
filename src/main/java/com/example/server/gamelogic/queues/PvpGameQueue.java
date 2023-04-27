package com.example.server.gamelogic.queues;

import com.example.server.gamelogic.models.Game;
import com.example.server.gamelogic.models.GameQueue;
import com.example.server.gamelogic.models.Player;

import java.util.List;

public class PvpGameQueue extends GameQueue {
    @Override
    protected int getAmountRequiredPlayers() {
        return 2;
    }

    @Override
    public String getModeName() {
        return "PVP";
    }

    @Override
    protected Game getNewGame(List<Player> players) {
        return new PvpGame(players);
    }
}
