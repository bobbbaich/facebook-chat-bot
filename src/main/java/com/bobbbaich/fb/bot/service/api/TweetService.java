package com.bobbbaich.fb.bot.service.api;

public interface TweetService {
    void collectTweets(String topicName, String tweetWord, Integer limit);
}
