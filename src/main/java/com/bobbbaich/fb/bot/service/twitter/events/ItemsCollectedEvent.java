package com.bobbbaich.fb.bot.service.twitter.events;

import org.springframework.context.ApplicationEvent;

public class ItemsCollectedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ItemsCollectedEvent(Object source) {
        super(source);
    }
}