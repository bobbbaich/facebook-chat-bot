package com.bobbbaich.fb.bot.supplier;

import com.bobbbaich.fb.bot.cache.api.EventPublisher;
import com.bobbbaich.fb.bot.listener.TwitterStreamListener;

import com.bobbbaich.fb.bot.supplier.api.StreamListenerSupplier;
import com.bobbbaich.kafka.producer.api.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterStreamListenerSupplier implements StreamListenerSupplier {
    private final Producer<String> producer;
    private final EventPublisher<Stream> publisher;

    @Override
    public StreamListener supply(final String topic, final String recipientId, final long streamNumber, final Integer limit) {
        return new TwitterStreamListener(producer, publisher, recipientId, topic, limit, streamNumber);
    }
}
