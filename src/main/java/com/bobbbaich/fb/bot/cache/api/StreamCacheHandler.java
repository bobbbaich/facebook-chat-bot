package com.bobbbaich.fb.bot.cache.api;

import org.springframework.social.twitter.api.Stream;

public interface StreamCacheHandler {
    Stream find(String topic, String keyWord);

    void close(String topic, String keyWord);

    void add(String topic, String keyWord, Stream stream);
}
