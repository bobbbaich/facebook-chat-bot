package com.bobbbaich.fb.bot.service;

public interface TwitterService {
    void run(String keyWord, String...otherWords) throws InterruptedException;
    void runKafka(String keyWord);
    void test();
}
