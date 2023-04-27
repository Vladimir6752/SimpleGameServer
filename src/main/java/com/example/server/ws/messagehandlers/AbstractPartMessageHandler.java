package com.example.server.ws.messagehandlers;

import org.springframework.web.socket.WebSocketSession;

public abstract class AbstractPartMessageHandler {
    protected final WebSocketSession session;

    protected AbstractPartMessageHandler(WebSocketSession session) {
        this.session = session;
    }

    protected abstract AbstractPartMessageHandler getNextHandlingState(String[] sharedPath) throws IllegalMessagePathException;

    /**
     * Template method
     * @param sharedPath eventType;caller;eventValue in String array
     * @return next state
     */
    public final AbstractPartMessageHandler handle(String[] sharedPath) throws IllegalMessagePathException {
        AbstractPartMessageHandler handlingState = getNextHandlingState(sharedPath);

        if(handlingState != null)
            return handlingState.handle(sharedPath);

        return null;
    }
}
