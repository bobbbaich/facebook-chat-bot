package com.bobbbaich.fb.bot.cache.api;

public interface Cache<T> {
    T add(String recipientId, long streamNumber, T obj);

    T get(String recipientId, long streamNumber);

    T remove(String recipientId, long streamNumber);
}
