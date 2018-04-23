package com.bobbbaich.fb.bot.cache;

import com.bobbbaich.fb.bot.cache.api.CacheController;
import com.bobbbaich.fb.bot.cache.api.CacheService;
import com.bobbbaich.fb.bot.cache.api.Event;
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
        if (!validateTopicAndKeyWord(streamInfo)) {
            throw new IllegalArgumentException("Validate stream info was failed");
        }
        log.debug("Try to close stream with topic = {} and keyWord = {}", streamInfo.getTopic(), streamInfo.getKeyWord());
        handler.close(streamInfo.getTopic(), streamInfo.getKeyWord());
    }

    public void add(Event<StreamInfo> event) {
        StreamInfo streamInfo = event.getEventObj();
        log.debug("Stream info = {}", streamInfo);
        if (!validate(streamInfo)) {
            throw new IllegalArgumentException("Validate stream info was failed");
        }
        log.debug("Try to add stream with topic = {} and keyWord = {}", streamInfo.getTopic(), streamInfo.getKeyWord());
        handler.add(streamInfo.getTopic(), streamInfo.getKeyWord(), streamInfo.getStream());
    }

    private boolean validate(StreamInfo streamInfo) {
        return validateTopicAndKeyWord(streamInfo) && validateStream(streamInfo);
    }

    private boolean validateTopicAndKeyWord(StreamInfo streamInfo) {
        String topic = streamInfo.getTopic();
        String keyWord = streamInfo.getKeyWord();
        return topic != null && keyWord != null && !topic.isEmpty() && !keyWord.isEmpty();
    }

    private boolean validateStream(StreamInfo streamInfo) {
        Stream stream = streamInfo.getStream();
        return stream != null;
    }
}
