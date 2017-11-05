package com.bobbbaich.fb.bot.service.twitter.listener;

import com.bobbbaich.fb.bot.model.Opinion;
import com.bobbbaich.fb.bot.model.SocialNetwork;
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

    private static Set<Opinion> opinionSet = new HashSet<>();

    @Autowired
    public TwitterStreamListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onTweet(Tweet tweet) {
        processTweet(tweet);
        LOG.debug("opinionSet size: {}", opinionSet.size());
        if (opinionSet.size() == 100) {
            this.publisher.publishEvent(new ItemsCollectedEvent(opinionSet));
        }
    }

    private void processTweet(Tweet tweet) {
        Opinion opinion = new Opinion();
        opinion.setPayload(tweet.getUnmodifiedText());
        opinion.setSocialNetwork(SocialNetwork.TWITTER);
        opinion.setCreatedAt(tweet.getCreatedAt());
        opinion.setRetweetCount(tweet.getRetweetCount());
        opinion.setFavoriteCount(tweet.getFavoriteCount());

        opinionSet.add(opinion);
    }
}
