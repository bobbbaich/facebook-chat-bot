package com.bobbbaich.fb.bot.messenger.controller;

import com.bobbbaich.fb.bot.messenger.exception.MessengerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class MessengerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({MessengerException.class})
    public ResponseEntity<Void> handleAccessDeniedException(Exception ex, WebRequest request) {
        log.warn("Event processing failed: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}