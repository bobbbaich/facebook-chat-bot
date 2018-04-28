package com.bobbbaich.fb.bot.messenger.processor;

import com.github.messenger4j.webhook.Event;

public interface ProcessorLookup {
    EventProcessor getProcessor(Event event);
}