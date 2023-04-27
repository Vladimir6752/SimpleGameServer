package com.example.server.utils;

public class TextMessageType {
    public static final String PING = "ping";
    public static final String PONG = "pong";
    public static final String GAME_EVENT = "ge";
    public static final String PLAYER_EVENT = "pe";
    public static final String MAKE_STEP_EVENT = "ms";
    public static final String ADD_PLAYER_IN_QUEUE = "aq";
    public static final String REMOVE_PLAYER_FROM_QUEUE = "rq";

    private TextMessageType() {}
}
