package com.bobbbaich.fb.bot.exception;

public class LimitTweetException extends Exception {
    public LimitTweetException() {
    }

    public LimitTweetException(String s) {
        super(s);
    }

    public LimitTweetException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public LimitTweetException(Throwable throwable) {
        super(throwable);
    }

    public LimitTweetException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
