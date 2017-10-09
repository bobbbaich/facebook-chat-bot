package com.bobbbaich.fb.bot.service.twitter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;

public class DefaultStreamListener implements StreamListener {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultStreamListener.class);

    public DefaultStreamListener() {
        super();
        LOG.debug("DefaultStreamListener created.");
    }

    @Override
    public void onTweet(Tweet tweet) {
        LOG.debug("onTweet");
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {
        LOG.debug("deleteEvent: userId({}) - tweet{}", deleteEvent.getUserId(), deleteEvent.getTweetId());
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
        LOG.debug("numberOfLimitedTweets: {}", numberOfLimitedTweets);
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
        LOG.debug("warningEvent: {} - {}", warningEvent.getCode(), warningEvent.getMessage());
    }
}
