package com.bobbbaich.twitter.supplier;

import com.bobbbaich.twitter.cache.StreamInfo;
import com.bobbbaich.twitter.cache.api.Event;
import com.bobbbaich.twitter.supplier.api.EventSupplier;
import com.bobbbaich.twitter.cache.api.annotaion.CloseStream;
import com.bobbbaich.twitter.cache.event.CloseEvent;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@CloseStream
@Component
public class CloseStreamEventSupplier implements EventSupplier<StreamInfo, Stream> {
    @Override
    public Event<StreamInfo> supply(Object source, StreamInfo streamInfo) {
        return new CloseEvent(source, streamInfo);
    }

    @Override
    public Event<StreamInfo> supply(Object source, String recipientId, long streamNumber) {
        StreamInfo streamInfo = new StreamInfo();
        streamInfo.setRecipientId(recipientId);
        streamInfo.setStreamNumber(streamNumber);
        return supply(source, streamInfo);
    }

    @Override
    public Event<StreamInfo> supply(Object source, String recipientId, long streamNumber, Stream stream) {
        throw new UnsupportedOperationException("Not supported for " + this.getClass().getSimpleName());
    }
}
