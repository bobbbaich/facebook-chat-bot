package com.bobbbaich.fb.bot.cache.event.supplier;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import com.bobbbaich.fb.bot.cache.api.StreamEventSupplier;
import com.bobbbaich.fb.bot.cache.api.annotaion.AddStream;
import com.bobbbaich.fb.bot.cache.event.AddStreamEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Component;

@AddStream
@Component
public class AddStreamEventSupplier implements StreamEventSupplier {
    @Override
    public ApplicationEvent supply(Object source, StreamInfo streamInfo) {
        return new AddStreamEvent(source, streamInfo);
    }

    @Override
    public ApplicationEvent supply(Object source, String topic, String keyWord) {
        throw new UnsupportedOperationException("Not supported for " + this.getClass().getSimpleName());
    }

    @Override
    public ApplicationEvent supply(Object source, String topic, String keyWord, Stream stream) {
        StreamInfo streamInfo = new StreamInfo();
        streamInfo.setTopic(topic);
        streamInfo.setKeyWord(keyWord);
        streamInfo.setStream(stream);
        return supply(source, streamInfo);
    }
}

