package com.bobbbaich.fb.bot.cache;

import com.bobbbaich.fb.bot.cache.api.StreamCacheHandler;
import com.bobbbaich.fb.bot.cache.api.StreamCacheService;
import com.bobbbaich.fb.bot.cache.event.AddStreamEvent;
import com.bobbbaich.fb.bot.cache.event.CloseStreamEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class StreamCacheServiceImpl implements StreamCacheService {
    private final StreamCacheHandler handler;

    @Override
    public void closeStream(CloseStreamEvent event) {
        StreamInfo streamInfo = event.getStreamInfo();
        if (!validateTopicAndKeyWord(streamInfo)) {
            throw new IllegalArgumentException("Validate stream info was failed");
        }
        log.debug("Try to close stream with topic = {} and keyWord = {}", streamInfo.getTopic(), streamInfo.getKeyWord());
        handler.close(streamInfo.getTopic(), streamInfo.getKeyWord());
    }

    @Override
    public void addStream(AddStreamEvent event) {
        StreamInfo streamInfo = event.getStreamInfo();
        log.debug("Stream info = {}", streamInfo);
        if (!validate(streamInfo)) {
            throw new IllegalArgumentException("Validate stream info was failed");
        }
        log.debug("Try to add stream with topic = {} and keyWord = {}", streamInfo.getTopic(), streamInfo.getKeyWord());
        handler.add(streamInfo.getTopic(), streamInfo.getKeyWord(), streamInfo.getStream());
    }

    private boolean validate(StreamInfo streamInfo) {
        boolean f1 = validateTopicAndKeyWord(streamInfo);
        boolean f2 = validateStream(streamInfo);
        log.debug("FLAGS F1 = {}, F2 = {}", f1, f2);
        return f1 && f2;
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
