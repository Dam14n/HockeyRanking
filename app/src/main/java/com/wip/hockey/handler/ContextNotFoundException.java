package com.wip.hockey.handler;

/**
 * Created by djorda on 12/07/2017.
 */

public class ContextNotFoundException extends Exception{
    public ContextNotFoundException(String message) {
        super(message);
    }

    public ContextNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
