package com.bobbbaich.messenger.service.api;

import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;

public interface BotService {
    void createUser(String recipientId) throws MessengerApiException, MessengerIOException;

    void startAnalysis(String recipientId) throws MessengerApiException, MessengerIOException;

    void sendTextResponse(String recipientId, String response) throws MessengerApiException, MessengerIOException;

    void getHelp(String recipientId) throws MessengerApiException, MessengerIOException;
}