package com.bobbbaich.twitter.service.api;

import com.bobbbaich.kafka.model.Message;

public interface TweetService {
    void collectTweets(Message message);
}
