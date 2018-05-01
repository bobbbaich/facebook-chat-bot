package com.bobbbaich.fb.bot.messenger.service;

public interface MessageProvider<T> {
    void send2Analyse(T obj);
}
