package com.example.server.ws.messagehandlers;

public class IllegalMessagePathException extends Exception {
    public IllegalMessagePathException(String s) {
        super(s);
    }
}
