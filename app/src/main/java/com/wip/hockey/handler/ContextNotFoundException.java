package com.wip.hockey.handler;


public class ContextNotFoundException extends Exception{
    public ContextNotFoundException() {
        super("Context is not defined.");
    }

    public ContextNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
