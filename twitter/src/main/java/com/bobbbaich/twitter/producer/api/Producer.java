package com.bobbbaich.twitter.producer.api;

public interface Producer<V> {
    void send(String topic, V value);
}
