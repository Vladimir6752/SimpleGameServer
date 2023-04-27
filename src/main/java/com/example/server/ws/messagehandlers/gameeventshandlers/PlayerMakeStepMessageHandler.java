package com.example.server.ws.messagehandlers.gameeventshandlers;

import com.example.server.ws.messagehandlers.AbstractPartMessageHandler;
import org.springframework.web.socket.WebSocketSession;

public class PlayerMakeStepMessageHandler extends AbstractPartMessageHandler {
    public PlayerMakeStepMessageHandler(WebSocketSession session) {
        super(session);
    }

    @Override
    protected AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) {
        String playerNickname = sharedPath[3];

        System.out.printf("Player %s make step%n", playerNickname);
        return null;
    }
}
