package com.bobbbaich.fb.bot.cache.api;

public interface Cache<T> {
    T add(String topic, String keyWord, T obj);

    T get(String topic, String keyWord);

    T remove(String topic, String keyWord);
}
