package com.example.server.ws.messagehandlers;

import com.example.server.ws.messagehandlers.gameeventshandlers.GameEventHandler;
import com.example.server.ws.messagehandlers.playereventshandlers.PlayerEventHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;

import static com.example.server.utils.TextMessageType.*;

public class BeginMessageHandler extends AbstractPartMessageHandler {
    public BeginMessageHandler(WebSocketSession session) {
        super(session);
    }

    @Override
    protected AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) throws IllegalMessagePathException {
        String currentHandlingPart = sharedPath[0];

        if(currentHandlingPart.equals(GAME_EVENT))
            return new GameEventHandler(session);
        else if(currentHandlingPart.equals(PLAYER_EVENT))
            return new PlayerEventHandler(session);

        throw new IllegalMessagePathException("Illegal path part: " + currentHandlingPart + " on " + Arrays.toString(sharedPath));
    }
}
