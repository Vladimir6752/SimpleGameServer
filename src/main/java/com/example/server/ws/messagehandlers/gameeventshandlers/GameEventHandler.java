package com.example.server.ws.messagehandlers.gameeventshandlers;

import com.example.server.ws.messagehandlers.IllegalMessagePathException;
import com.example.server.ws.messagehandlers.AbstractPartMessageHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;

import static com.example.server.utils.TextMessageType.*;

public class GameEventHandler extends AbstractPartMessageHandler {
    public GameEventHandler(WebSocketSession session) {
        super(session);
    }

    @Override
    protected AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) throws IllegalMessagePathException {
        String currentHandlingPart = sharedPath[1];

        if(currentHandlingPart.equals(MAKE_STEP_EVENT))
            return new PlayerMakeStepMessageHandler(session);

        throw new IllegalMessagePathException("Illegal path part: " + currentHandlingPart + " on " + Arrays.toString(sharedPath));
    }
}
