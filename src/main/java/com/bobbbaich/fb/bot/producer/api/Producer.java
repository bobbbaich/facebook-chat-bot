package com.bobbbaich.fb.bot.producer.api;

public interface Producer<V> {
    void send(String topic, V value);
}
