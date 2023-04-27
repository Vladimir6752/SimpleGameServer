package com.example.server.gamelogic.models;

import org.springframework.web.socket.WebSocketSession;

public class Player {
    private final String nickName;
    private final WebSocketSession session;
    private int startWaitingTime = 0;

    public Player(String nickName, WebSocketSession session) {
        this.nickName = nickName;
        this.session = session;
    }

    public String getNickName() {
        return nickName;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public int getStartWaitingTime() {
        return startWaitingTime;
    }

    public void setStartWaitingTime(int startWaitingTime) {
        this.startWaitingTime = startWaitingTime;
    }
}
