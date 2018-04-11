package com.bobbbaich.fb.bot.kafka.api;

import org.springframework.social.twitter.api.StreamListener;

public interface StreamListenerProvider {
    StreamListener provide(String topicName, String keyWord, Integer limit);
}
