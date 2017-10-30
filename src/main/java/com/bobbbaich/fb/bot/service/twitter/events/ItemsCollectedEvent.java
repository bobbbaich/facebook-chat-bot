package com.bobbbaich.fb.bot.service.twitter.events;

import org.springframework.context.ApplicationEvent;

import java.util.Set;

public class ItemsCollectedEvent<T> extends ApplicationEvent implements ItemsEvent<T> {

    public ItemsCollectedEvent(Object source) {
        super(source);
        throw new IllegalArgumentException("ItemsCollectedEvent source must be Set<T>. Use another constructor!");
    }

    public ItemsCollectedEvent(Set<T> source) {
        super(source);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<T> getItems() {
        return (Set<T>) this.source;
    }
}