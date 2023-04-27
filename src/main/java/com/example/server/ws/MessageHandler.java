package com.example.server.ws;

import com.example.server.ws.messagehandlers.BeginMessageHandler;
import com.example.server.ws.messagehandlers.IllegalMessagePathException;
import org.springframework.web.socket.WebSocketSession;

public class MessageHandler {
    private final String message;
    private final WebSocketSession session;
    private static final String SPLIT_PATH_REGEX = ";";

    public MessageHandler(String message, WebSocketSession session) {
        this.message = message;
        this.session = session;
    }

    public void handleMessage() {
        try {
            new BeginMessageHandler(session).handle(message.split(SPLIT_PATH_REGEX));
        } catch (IllegalMessagePathException e) {
            System.out.println(e.getMessage());
        }
    }
}
