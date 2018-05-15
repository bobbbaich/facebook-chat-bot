package com.bobbbaich.twitter.cache;

import com.bobbbaich.twitter.cache.api.CacheController;
import com.bobbbaich.twitter.cache.api.CacheService;
import com.bobbbaich.twitter.cache.api.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class StreamCacheService implements CacheService<Event<StreamInfo>> {
    private final CacheController<Stream> handler;

    public void remove(Event<StreamInfo> event) {
        StreamInfo streamInfo = event.getEventObj();
        if (!validateRecipientId(streamInfo)) {
            throw new IllegalArgumentException("Validate stream info was failed");
        }
        log.debug("Try to close stream with recipientId = {} and stream number = {}", streamInfo.getRecipientId(), streamInfo.getStreamNumber());
        handler.close(streamInfo.getRecipientId(), streamInfo.getStreamNumber());
    }

    public void add(Event<StreamInfo> event) {
        StreamInfo streamInfo = event.getEventObj();
        log.debug("Stream info = {}", streamInfo);
        if (!validate(streamInfo)) {
            throw new IllegalArgumentException("Validate stream info was failed");
        }
        log.debug("Try to add stream with recipientId = {} and stream number = {}", streamInfo.getRecipientId(), streamInfo.getStreamNumber());
        handler.add(streamInfo.getRecipientId(), streamInfo.getStreamNumber(), streamInfo.getStream());
    }

    private boolean validate(StreamInfo streamInfo) {
        return validateRecipientId(streamInfo) && validateStream(streamInfo);
    }

    private boolean validateRecipientId(StreamInfo streamInfo) {
        String recipientId = streamInfo.getRecipientId();
        return recipientId != null && !recipientId.isEmpty();
    }

    private boolean validateStream(StreamInfo streamInfo) {
        Stream stream = streamInfo.getStream();
        return stream != null;
    }
}
