package com.bobbbaich.messenger.producer.api;

public interface Producer<V> {
    void send(String topic, V value);
}
