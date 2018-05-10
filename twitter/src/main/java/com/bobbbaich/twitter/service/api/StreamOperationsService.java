package com.bobbbaich.twitter.service.api;

import org.springframework.social.twitter.api.Stream;

public interface StreamOperationsService {
    Stream runStream(String recipientId, String topicName, String tweetWord, Integer limit);
}
