package com.bobbbaich.messenger.processor.delegate;

import com.github.messenger4j.webhook.Event;

public interface EventDelegate {
    void onEvent(Event event);
}