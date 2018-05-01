package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;

import java.net.MalformedURLException;

public interface BotService {
    void quickReply(String recipientId) throws MessengerApiException, MessengerIOException;

    void createUser(String recipientId) throws MessengerApiException, MessengerIOException;
}
