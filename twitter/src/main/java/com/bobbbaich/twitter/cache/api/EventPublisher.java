package com.bobbbaich.twitter.cache.api;

public interface EventPublisher<T> {

    void add(String recipientId, long streamNumber, T obj);

    void close(String recipientId, long streamNumber);
}
