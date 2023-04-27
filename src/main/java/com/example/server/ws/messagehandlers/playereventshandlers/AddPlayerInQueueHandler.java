package com.example.server.ws.messagehandlers.playereventshandlers;

import com.example.server.gamelogic.managers.GameQueueManager;
import com.example.server.ws.messagehandlers.AbstractPartMessageHandler;
import org.springframework.web.socket.WebSocketSession;

public class AddPlayerInQueueHandler extends AbstractPartMessageHandler {
    public AddPlayerInQueueHandler(WebSocketSession session) {
        super(session);
    }

    @Override
    protected AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) {
        String playerNickname = sharedPath[2];
        String gameMode = sharedPath[3];

        GameQueueManager.getInstance().tryToAddPlayerInQueue(playerNickname, gameMode, session);
        return null;
    }
}
