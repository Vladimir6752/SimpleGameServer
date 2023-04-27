package com.example.server.gamelogic.models;

import com.example.server.gamelogic.managers.GameManager;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Template method
 */
public abstract class GameQueue {
    private final List<Player> playersInQueue = new CopyOnWriteArrayList<>();

    protected GameQueue() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tryToCreateGame();
                notifyWaitingPlayers();
            }
        }, 0, 1000);
    }

    protected abstract int getAmountRequiredPlayers();

    public abstract String getModeName();

    protected abstract Game getNewGame(List<Player> players);

    public void addPlayerInQueue(String nickName, WebSocketSession session) {
        System.out.printf("%s join to queue in %s%n", nickName, getModeName());
        playersInQueue.add(new Player(nickName, session));
    }

    public void removePlayerFromQueue(String nickName) {
        if(playersInQueue.removeIf(player -> player.getNickName().equals(nickName)))
            System.out.printf("%s quit queue in %s%n", nickName, getModeName());
    }

    public void removePlayerFromQueue(Player player) {
        removePlayerFromQueue(player.getNickName());
    }

    private void tryToCreateGame() {
        if(playersInQueue.size() < getAmountRequiredPlayers()) return;

        List<Player> chosePlayers = playersInQueue.subList(
                0,
                getAmountRequiredPlayers()
        );

        String notifyMessage = String.format("gs;%s;%s", getModeName(), getStringsOfPlayers(chosePlayers));
        for (Player player : chosePlayers) {
            try {
                player.getSession().sendMessage(
                        new TextMessage(notifyMessage)
                );
            } catch (Exception ignored) {}
            removePlayerFromQueue(player);
        }

        GameManager.getInstance().addNewGame(
                getNewGame(chosePlayers)
        );
    }

    private String getStringsOfPlayers(List<Player> chosePlayers) {
        return chosePlayers
                .stream()
                .collect(
                        StringBuilder::new,
                        (stringBuilder, player) -> stringBuilder.append(player.getNickName()).append(","),
                        StringBuilder::append
                )
                .toString();
    }

    /**
     * Observer
     */
    private void notifyWaitingPlayers() {
        for (Player player : playersInQueue) {
            try {
                player.setStartWaitingTime(player.getStartWaitingTime() + 1);

                player.getSession().sendMessage(
                        new TextMessage(String.format("t;%d", player.getStartWaitingTime()))
                );
            } catch (Exception e) {
                removePlayerFromQueue(player);
            }
        }
    }
}
