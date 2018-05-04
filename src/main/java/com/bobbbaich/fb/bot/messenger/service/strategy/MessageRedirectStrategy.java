package com.bobbbaich.fb.bot.messenger.service.strategy;

public interface MessageRedirectStrategy<T> {
    void redirect(T obj);
}