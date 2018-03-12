package com.bobbbaich.fb.bot.exceptions.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.CancellationException;

@ControllerAdvice
public class LimitTweetExceptionHandler {


    @ExceptionHandler(CancellationException.class)
    public String limitTweetHandler(Exception exception) {
        return exception.getMessage();
    }
}
