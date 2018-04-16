package com.bobbbaich.fb.bot.kafka.api;

public interface BrokerProducer<V> {
    void send(String topicName, V value);
}
