package com.bobbbaich.fb.bot.service.twitter.listener;

import com.bobbbaich.fb.bot.service.twitter.events.ItemsCollectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class TwitterStreamListener extends DefaultStreamListener {
    private final static Logger LOG = LoggerFactory.getLogger(TwitterStreamListener.class);
    private final ApplicationEventPublisher publisher;

    private static Set<Tweet> tweetSet = new HashSet<>();

    @Autowired
    public TwitterStreamListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onTweet(Tweet tweet) {
        tweetSet.add(tweet);
        LOG.debug("tweetSet size: {}", tweetSet.size());
        if (tweetSet.size() == 10) {
            this.publisher.publishEvent(new ItemsCollectedEvent<>(tweetSet));
        }
    }
}
