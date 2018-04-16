package com.bobbbaich.fb.bot.cache.api;

import com.bobbbaich.fb.bot.cache.StreamInfo;
import org.springframework.context.ApplicationEvent;
import org.springframework.social.twitter.api.Stream;

public interface StreamEventSupplier {
    ApplicationEvent supply(Object source, StreamInfo streamInfo);

    ApplicationEvent supply(Object source, String topic, String keyWord);

    ApplicationEvent supply(Object source, String topic, String keyWord, Stream stream);
}
