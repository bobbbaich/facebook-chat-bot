package com.bobbbaich.fb.bot.cache.event.supplier;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.StreamEventSupplier;
import com.bobbbaich.fb.bot.cache.api.annotaion.CloseStream;
import com.bobbbaich.fb.bot.cache.event.CloseStreamEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@CloseStream
@Component
public class CloseStreamEventSupplier implements StreamEventSupplier {
    @Override
    public ApplicationEvent supply(Object source, StreamInfo streamInfo) {
        return new CloseStreamEvent(source, streamInfo);
    }

    @Override
    public ApplicationEvent supply(Object source, String topic, String keyWord) {
        StreamInfo streamInfo = new StreamInfo();
        streamInfo.setTopic(topic);
        streamInfo.setKeyWord(keyWord);
        return supply(source, streamInfo);
    }

    @Override
    public ApplicationEvent supply(Object source, String topic, String keyWord, Stream stream) {
        throw new UnsupportedOperationException("Not supported for " + this.getClass().getSimpleName());
    }
}
