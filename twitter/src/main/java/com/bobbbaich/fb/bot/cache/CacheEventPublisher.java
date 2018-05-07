package com.bobbbaich.fb.bot.cache;

import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.cache.api.EventPublisher;
import com.bobbbaich.fb.bot.cache.api.annotaion.AddStream;
import com.bobbbaich.fb.bot.cache.api.annotaion.CloseStream;
import com.bobbbaich.fb.bot.supplier.api.EventSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheEventPublisher implements EventPublisher<Stream> {
    private final EventSupplier<StreamInfo, Stream> addEventSupplier;
    private final EventSupplier<StreamInfo, Stream> closeEventSupplier;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public CacheEventPublisher(@AddStream EventSupplier<StreamInfo, Stream> addEventSupplier,
                               @CloseStream EventSupplier<StreamInfo, Stream> closeEventSupplier,
                               ApplicationEventPublisher applicationEventPublisher) {
        this.addEventSupplier = addEventSupplier;
        this.closeEventSupplier = closeEventSupplier;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    private void publish(Event<StreamInfo> event) {
        applicationEventPublisher.publishEvent(event);
        log.debug("Stream cache event was published");
    }

    public void add(String recipientId, long streamNumber, Stream stream) {
        log.debug("Try to publish add stream event");
        publish(addEventSupplier.supply(this, recipientId, streamNumber, stream));
    }

    public void close(String recipientId, long streamNumber) {
        log.debug("Try to publish close stream event");
        publish(closeEventSupplier.supply(this, recipientId, streamNumber));
    }
}
