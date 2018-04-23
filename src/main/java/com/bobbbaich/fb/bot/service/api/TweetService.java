package com.bobbbaich.fb.bot.service.api;

public interface TweetService {
    void collectTweets(String tweetWord);

    void collectTweets(String tweetWord, Integer limit);

    void collectTweets(String topicName, String tweetWord, Integer limit);
}
