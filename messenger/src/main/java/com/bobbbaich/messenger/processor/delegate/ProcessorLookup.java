package com.bobbbaich.messenger.processor.delegate;

import com.bobbbaich.messenger.processor.api.EventProcessor;
import com.github.messenger4j.webhook.Event;

public interface ProcessorLookup {
    EventProcessor getProcessor(Event event);
}