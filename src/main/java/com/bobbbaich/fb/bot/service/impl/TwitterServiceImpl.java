package com.bobbbaich.fb.bot.service.impl;

import com.bobbbaich.fb.bot.service.TwitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterServiceImpl implements TwitterService {
    private final StreamingOperations streamingOperations;

    @Override
    public void run() {
        log.debug("Stream running...");
        streamingOperations.filter("trump", Collections.singletonList(new StreamListener() {
            @Override
            public void onTweet(Tweet tweet) {
                log.debug("onTweet: {}", tweet.getText());
            }

            @Override
            public void onDelete(StreamDeleteEvent deleteEvent) {
                log.debug("deleteEvent: {}", deleteEvent.getTweetId());
            }

            @Override
            public void onLimit(int numberOfLimitedTweets) {
                log.debug("numberOfLimitedTweets: {}", numberOfLimitedTweets);
            }

            @Override
            public void onWarning(StreamWarningEvent warningEvent) {
                log.debug("onWarning: {}", warningEvent.getMessage());
            }
        }));
    }
}