package com.bobbbaich.messenger.service.strategy;

public interface MessageRedirectStrategy<T> {
    void redirect(T obj);
}