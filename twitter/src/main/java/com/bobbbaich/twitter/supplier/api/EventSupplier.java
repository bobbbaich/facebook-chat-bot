package com.bobbbaich.twitter.supplier.api;

import com.bobbbaich.twitter.cache.api.Event;

public interface EventSupplier<T, X> {
    Event<T> supply(Object source, T eventObj);

    Event<T> supply(Object source, String recipientId, long streamNumber);

    Event<T> supply(Object source, String recipientId, long streamNumber, X obj);
}
