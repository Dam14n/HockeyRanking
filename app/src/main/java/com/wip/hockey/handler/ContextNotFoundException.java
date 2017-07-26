package com.wip.hockey.handler;

/**
 * Created by djorda on 12/07/2017.
 */

public class ContextNotFoundException extends Exception{
    public ContextNotFoundException() {
        super("Context is not defined.");
    }

    public ContextNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
