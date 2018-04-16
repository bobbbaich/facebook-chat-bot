package com.bobbbaich.fb.bot.cache.api;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.social.twitter.api.Stream;

public interface StreamCacheEventPublisher {
    void publish(ApplicationEvent event);

    void publishAdd(String topic, String keyWord, Stream stream);

    void publishClose(String topic, String keyWord);
}
