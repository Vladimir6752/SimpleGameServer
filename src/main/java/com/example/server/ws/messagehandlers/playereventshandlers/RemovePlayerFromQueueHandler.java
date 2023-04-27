package com.example.server.ws.messagehandlers.playereventshandlers;

import com.example.server.gamelogic.managers.GameQueueManager;
import com.example.server.ws.messagehandlers.AbstractPartMessageHandler;
import org.springframework.web.socket.WebSocketSession;

public class RemovePlayerFromQueueHandler extends AbstractPartMessageHandler {
    public RemovePlayerFromQueueHandler(WebSocketSession session) {
        super(session);
    }

    @Override
    protected AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) {
        String playerNickname = sharedPath[2];

        GameQueueManager.getInstance().tryToRemovePlayerFromQueue(playerNickname);
        return null;
    }
}
