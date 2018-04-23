package com.bobbbaich.fb.bot.cache;

import com.bobbbaich.fb.bot.cache.api.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class StreamCache implements Cache<Stream> {
    private Map<String, Stream> streamCache;

    public StreamCache() {
        this.streamCache = new ConcurrentHashMap<>();
    }

    @Override
    public Stream add(String topic, String keyWord, Stream stream) {
        log.debug("Add stream with key = {}", topic + keyWord);
        return streamCache.putIfAbsent(topic + keyWord, stream);
    }

    @Override
    public Stream get(String topic, String keyWord) {
        log.debug("Get stream with key = {}", topic + keyWord);
        return streamCache.get(topic + keyWord);
    }

    @Override
    public Stream remove(String topic, String keyWord) {
        log.debug("Remove stream with key = {}", topic + keyWord);
        return streamCache.remove(topic + keyWord);
    }
}
