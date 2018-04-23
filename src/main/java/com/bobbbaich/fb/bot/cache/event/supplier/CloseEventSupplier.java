package com.bobbbaich.fb.bot.cache.event.supplier;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.cache.api.EventSupplier;
import com.bobbbaich.fb.bot.cache.api.annotaion.CloseStream;
import com.bobbbaich.fb.bot.cache.event.CloseEvent;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@CloseStream
@Component
public class CloseEventSupplier implements EventSupplier<StreamInfo, Stream> {
    @Override
    public Event<StreamInfo> supply(Object source, StreamInfo streamInfo) {
        return new CloseEvent(source, streamInfo);
    }

    @Override
    public Event<StreamInfo> supply(Object source, String topic, String keyWord) {
        StreamInfo streamInfo = new StreamInfo();
        streamInfo.setTopic(topic);
        streamInfo.setKeyWord(keyWord);
        return supply(source, streamInfo);
    }

    @Override
    public Event<StreamInfo> supply(Object source, String topic, String keyWord, Stream stream) {
        throw new UnsupportedOperationException("Not supported for " + this.getClass().getSimpleName());
    }
}
