package com.bobbbaich.fb.bot.messenger.processor.api;

import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.BaseEvent;

public interface EventProcessor<T extends BaseEvent> {
    void doProcessing(T event) throws MessengerApiException, MessengerIOException;

    void doProcessing(Event event) throws MessengerApiException, MessengerIOException;
}