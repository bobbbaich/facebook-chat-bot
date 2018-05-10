package com.bobbbaich.kafka.producer.api;

public interface Producer<V> {
    void send(String topic, V value);
}
