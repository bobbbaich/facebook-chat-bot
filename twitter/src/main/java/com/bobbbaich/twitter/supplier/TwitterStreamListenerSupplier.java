package com.bobbbaich.twitter.supplier;

import com.bobbbaich.kafka.model.TweetMessage;
import com.bobbbaich.kafka.producer.api.Producer;
import com.bobbbaich.twitter.cache.api.EventPublisher;
import com.bobbbaich.twitter.listener.TwitterStreamListener;
import com.bobbbaich.twitter.supplier.api.StreamListenerSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterStreamListenerSupplier implements StreamListenerSupplier {
    private final Producer<TweetMessage> producer;
    private final EventPublisher<Stream> publisher;

    @Override
    public StreamListener supply(final String topic, final String recipientId, final long streamNumber, final Integer limit) {
        return new TwitterStreamListener(producer, publisher, recipientId, topic, limit, streamNumber);
    }
}
