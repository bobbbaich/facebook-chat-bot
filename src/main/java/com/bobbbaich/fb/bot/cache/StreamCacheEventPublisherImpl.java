package com.bobbbaich.fb.bot.cache;

import com.bobbbaich.fb.bot.cache.api.StreamCacheEventPublisher;
import com.bobbbaich.fb.bot.cache.api.StreamEventSupplier;
import com.bobbbaich.fb.bot.cache.api.annotaion.AddStream;
import com.bobbbaich.fb.bot.cache.api.annotaion.CloseStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StreamCacheEventPublisherImpl implements StreamCacheEventPublisher {
    private final StreamEventSupplier addEventSupplier;
    private final StreamEventSupplier closeEventSupplier;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public StreamCacheEventPublisherImpl(@AddStream StreamEventSupplier addEventSupplier,
                                         @CloseStream StreamEventSupplier closeEventSupplier,
                                         ApplicationEventPublisher applicationEventPublisher) {
        this.addEventSupplier = addEventSupplier;
        this.closeEventSupplier = closeEventSupplier;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void publish(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
        log.debug("Stream cache event was published");
    }

    public void publishAdd(String topic, String keyWord, Stream stream) {
        log.debug("Try to publish add stream event");
        publish(addEventSupplier.supply(this, topic, keyWord, stream));
    }

    public void publishClose(String topic, String keyWord) {
        log.debug("Try to publish close stream event");
        publish(closeEventSupplier.supply(this, topic, keyWord));
    }
}
