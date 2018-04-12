package com.bobbbaich.fb.bot.kafka.api;

import org.springframework.social.twitter.api.Stream;

public interface StreamOperationsService {
    Stream runStream(String topicName, String tweetWord, Integer limit);
}
