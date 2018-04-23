package com.bobbbaich.fb.bot.cache.api;

public interface EventSupplier<T, X> {
    Event<T> supply(Object source, T eventObj);

    Event<T> supply(Object source, String topic, String keyWord);

    Event<T> supply(Object source, String topic, String keyWord, X obj);
}
