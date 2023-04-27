package com.example.server.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.server.utils.TextMessageType.*;

@Component
public class SocketHandler extends TextWebSocketHandler {
    private static final List<WebSocketSession> sessions = new ArrayList<>();

    public void sendMessageToAll(String message) {
        for (WebSocketSession s : sessions) {
            try {
                s.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                System.out.println("ERROR SEND TO: " + s.getId());
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        if (handlePingMessage(session, message)) return;

        new MessageHandler(message.getPayload(), session).handleMessage();

        //System.out.println("SERVER CATCH: " + message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("ADD SESSION: " + session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.printf("REMOVE SESSION: %s WITH REASON %s%n", session.getId(), status.getReason());
        sessions.remove(session);
    }

    private static boolean handlePingMessage(WebSocketSession session, TextMessage message) {
        if(message.getPayload().equals(PING)) {
            try {
                session.sendMessage(new TextMessage(PONG));
                return true;
            } catch (Exception e) {
                System.out.println("ERROR SEND PONG TO: " + session.getId());
            }
        }
        return false;
    }
}
