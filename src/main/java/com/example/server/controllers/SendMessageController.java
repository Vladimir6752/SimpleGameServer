package com.example.server.controllers;

import com.example.server.ws.SocketHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    private final SocketHandler socketHandler;

    public SendMessageController(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @GetMapping("/send/{message}")
    public void sendToAll(@PathVariable String message) {
        socketHandler.sendMessageToAll(message);
    }
}
