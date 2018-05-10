package com.bobbbaich.twitter.cache.api;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public abstract class Event<T> extends ApplicationEvent {
    private T eventObj;
    public Event(Object source, T eventObj) {
        super(source);
        this.eventObj = eventObj;
    }
}
