package com.bobbbaich.fb.bot.kafka;

import com.bobbbaich.fb.bot.cache.api.StreamCacheEventPublisher;
import com.bobbbaich.fb.bot.kafka.api.StreamListenerProvider;
import com.bobbbaich.fb.bot.kafka.api.StreamOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class StreamOperationsServiceImpl implements StreamOperationsService {
    private final StreamingOperations streamingOperations;
    private final StreamListenerProvider listenerProvider;
    private final StreamCacheEventPublisher publisher;

    @Override
    public Stream runStream(String topicName, String tweetWord, Integer limit) {
        Stream stream = streamingOperations.filter(tweetWord,
                Collections.singletonList(listenerProvider.provide(topicName, tweetWord, limit)));
        publisher.publishAdd(topicName, tweetWord, stream);
        return stream;
    }
}
