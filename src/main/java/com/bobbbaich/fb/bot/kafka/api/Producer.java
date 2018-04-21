package com.bobbbaich.fb.bot.kafka.api;

public interface Producer<V> {
    void send(String topicName, V value);
}
