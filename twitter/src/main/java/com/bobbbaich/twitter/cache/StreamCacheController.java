package com.bobbbaich.twitter.cache;

import com.bobbbaich.twitter.cache.api.Cache;
import com.bobbbaich.twitter.cache.api.CacheController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamCacheController implements CacheController<Stream> {
    private final Cache<Stream> streamCache;

    @Override
    public Stream find(String recipientId, long streamNumber) {
        return streamCache.get(recipientId, streamNumber);
    }

    @Override
    public void close(String recipientId, long streamNumber) {
        Stream stream = streamCache.get(recipientId, streamNumber);
        stream.close();
        log.debug("Stream by recipientId = {} and streamNumber = {} was closed", recipientId, streamNumber);
        streamCache.remove(recipientId, streamNumber);
        log.debug("Stream by recipientId = {} and streamNumber = {} was removed from cache", recipientId, streamNumber);
    }

    @Override
    public void add(String topic, long streamNumber, Stream stream) {
        streamCache.add(topic, streamNumber, stream);
        log.debug("Stream with recipientId = {} and streamNumber = {} was added to cache", topic, streamNumber);
    }
}
