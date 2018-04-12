package com.bobbbaich.fb.bot.cache;

import com.bobbbaich.fb.bot.cache.api.StreamCache;
import com.bobbbaich.fb.bot.cache.api.StreamCacheHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamCacheHandlerImpl implements StreamCacheHandler {
    private final StreamCache streamCache;

    @Override
    public Stream find(String topic, String keyWord) {
        return streamCache.get(topic, keyWord);
    }

    @Override
    public void close(String topic, String keyWord) {
        Stream stream = streamCache.get(topic, keyWord);
        stream.close();
        log.debug("Stream by topic = {} and keyWord = {} was closed", topic, keyWord);
        streamCache.remove(topic, keyWord);
        log.debug("Stream by topic = {} and keyWord = {} was removed from cache", topic, keyWord);
    }

    @Override
    public void add(String topic, String keyWord, Stream stream) {
        streamCache.add(topic, keyWord, stream);
        log.debug("Stream with topic = {} and keyWord = {} was added to cache", topic, keyWord);
    }
}
