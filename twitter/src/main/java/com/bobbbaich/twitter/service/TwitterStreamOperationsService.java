package com.bobbbaich.twitter.service;

import com.bobbbaich.twitter.cache.api.EventPublisher;
import com.bobbbaich.twitter.service.api.StreamOperationsService;
import com.bobbbaich.twitter.supplier.api.StreamListenerSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterStreamOperationsService implements StreamOperationsService {
    private final StreamingOperations streamingOperations;
    private final StreamListenerSupplier listenerProvider;
    private final EventPublisher<Stream> publisher;
    private long streamNumber;

    @Override
    public Stream runStream(String recipientId, String topicName, String tweetWord, Integer limit) {
        Stream stream = streamingOperations.filter(tweetWord, Collections.singletonList(listenerProvider.supply(topicName, recipientId, ++streamNumber, limit)));
        log.debug("Stream created");
        publisher.add(recipientId, streamNumber, stream);
        log.debug("Stream added in cache");
        return stream;
    }
}