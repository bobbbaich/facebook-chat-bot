package com.bobbbaich.fb.bot.cache.api;

import org.springframework.social.twitter.api.Stream;

public interface StreamCache {
    Stream add(String topic, String keyWord, Stream stream);

    Stream get(String topic, String keyWord);

    Stream remove(String topic, String keyWord);
}
