package com.bobbbaich.fb.bot.service.api;

import com.bobbbaich.fb.bot.model.Message;

public interface TweetService {
    void collectTweets(Message message);
}
