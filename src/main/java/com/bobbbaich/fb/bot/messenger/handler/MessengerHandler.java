package com.bobbbaich.fb.bot.messenger.handler;

import com.github.messenger4j.webhook.event.BaseEvent;

public interface MessengerHandler<T extends BaseEvent> {
    void handle(T event);
}
