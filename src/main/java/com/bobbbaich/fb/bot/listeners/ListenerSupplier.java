package com.bobbbaich.fb.bot.listeners;

import com.bobbbaich.fb.bot.kafka.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ListenerSupplier {
    private final Sender sender;

//    @Value("${kafka.topic.twitter}")
    private String twitterTopic = "tweet.t";

    public StreamListener supplyTwitterListener() {
        return new StreamListener() {
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
        };
    }

    public StreamListener supplyKafkaTwitterListener() {
        return new StreamListener() {
            @Override
            public void onTweet(Tweet tweet) {
                sender.send(twitterTopic, tweet.getText());
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
        };
    }
}
