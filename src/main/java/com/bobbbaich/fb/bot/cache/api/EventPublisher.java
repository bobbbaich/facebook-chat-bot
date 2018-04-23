package com.bobbbaich.fb.bot.cache.api;

public interface EventPublisher<T> {

    void add(String topic, String keyWord, T obj);

    void close(String topic, String keyWord);
}
