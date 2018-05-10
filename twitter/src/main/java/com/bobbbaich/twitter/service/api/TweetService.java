package com.bobbbaich.twitter.service.api;


import com.bobbbaich.twitter.model.Message;

public interface TweetService {
    void collectTweets(Message message);
}
