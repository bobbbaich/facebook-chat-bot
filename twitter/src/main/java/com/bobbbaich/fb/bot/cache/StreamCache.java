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
    private String keyPattern = "%s_%d";

    public StreamCache() {
        this.streamCache = new ConcurrentHashMap<>();
    }

    @Override
    public Stream add(String recipientId, long streamNumber, Stream stream) {
        log.debug("Add stream with key = {}", String.format(keyPattern,recipientId,streamNumber));
        return streamCache.putIfAbsent(String.format(keyPattern,recipientId,streamNumber), stream);
    }

    @Override
    public Stream get(String recipientId, long streamNumber) {
        log.debug("Get stream with key = {}", String.format(keyPattern,recipientId,streamNumber));
        return streamCache.get(String.format(keyPattern,recipientId,streamNumber));
    }

    @Override
    public Stream remove(String recipientId, long streamNumber) {
        log.debug("Remove stream with key = {}", String.format(keyPattern,recipientId,streamNumber));
        return streamCache.remove(String.format(keyPattern,recipientId,streamNumber));
    }
}
