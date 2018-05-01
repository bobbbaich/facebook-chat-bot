package com.bobbbaich.fb.bot.cache.api;

public interface CacheController<T> {
    T find(String recipientId, long streamNumber);

    void close(String recipientId, long streamNumber);

    void add(String recipientId, long streamNumber, T obj);
}
