package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.BaseEvent;

public interface EventProcessor<T extends BaseEvent> {
    void doProcessing(T event);

    void doProcessing(Event event);
}