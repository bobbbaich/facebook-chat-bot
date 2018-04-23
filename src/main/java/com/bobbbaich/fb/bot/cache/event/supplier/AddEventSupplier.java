package com.bobbbaich.fb.bot.cache.event.supplier;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.cache.api.EventSupplier;
import com.bobbbaich.fb.bot.cache.api.annotaion.AddStream;
import com.bobbbaich.fb.bot.cache.event.AddEvent;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@AddStream
@Component
public class AddEventSupplier implements EventSupplier<StreamInfo, Stream> {
    @Override
    public Event<StreamInfo> supply(Object source, StreamInfo streamInfo) {
        return new AddEvent(source, streamInfo);
    }

    @Override
    public Event<StreamInfo> supply(Object source, String topic, String keyWord) {
        throw new UnsupportedOperationException("Not supported for " + this.getClass().getSimpleName());
    }

    @Override
    public Event<StreamInfo> supply(Object source, String topic, String keyWord, Stream stream) {
        StreamInfo streamInfo = new StreamInfo();
        streamInfo.setTopic(topic);
        streamInfo.setKeyWord(keyWord);
        streamInfo.setStream(stream);
        return supply(source, streamInfo);
    }
}

