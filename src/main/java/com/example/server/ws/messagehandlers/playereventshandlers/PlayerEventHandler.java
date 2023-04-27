package com.example.server.ws.messagehandlers.playereventshandlers;

import com.example.server.ws.messagehandlers.IllegalMessagePathException;
import com.example.server.ws.messagehandlers.AbstractPartMessageHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;

import static com.example.server.utils.TextMessageType.*;

public class PlayerEventHandler extends AbstractPartMessageHandler {
    public PlayerEventHandler(WebSocketSession session) {
        super(session);
    }

    @Override
    protected AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) throws IllegalMessagePathException{
        String currentHandlingPart = sharedPath[1];

        if(currentHandlingPart.equals(ADD_PLAYER_IN_QUEUE))
            return new AddPlayerInQueueHandler(session);
        else if(currentHandlingPart.equals(REMOVE_PLAYER_FROM_QUEUE))
            return new RemovePlayerFromQueueHandler(session);

        throw new IllegalMessagePathException("Illegal path part: " + currentHandlingPart + " on " + Arrays.toString(sharedPath));
    }
}
