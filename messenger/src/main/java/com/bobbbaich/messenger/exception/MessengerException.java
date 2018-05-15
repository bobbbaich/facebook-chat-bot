package com.bobbbaich.messenger.exception;

public class MessengerException extends RuntimeException {
    public MessengerException(String message, Throwable cause) {
        super(message, cause);
    }
}