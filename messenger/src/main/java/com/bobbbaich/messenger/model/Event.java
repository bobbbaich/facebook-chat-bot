package com.bobbbaich.messenger.model;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class Event<T> extends ApplicationEvent {
    private T eventObj;
    public Event(Object source, T eventObj) {
        super(source);
        this.eventObj = eventObj;
    }
}
