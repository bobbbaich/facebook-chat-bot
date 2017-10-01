package com.bobbbaich.fb.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {
    private static final Logger LOG = LoggerFactory.getLogger(SocialService.class);

    @Autowired
    private Facebook facebook;

    @Autowired
    private Twitter twitter;

    public void test() {
        LOG.debug("FacebookService test()");

        long profileId = twitter.userOperations().getProfileId();

        List<Tweet> homeTimeline = twitter.timelineOperations().getHomeTimeline();

        System.out.println();
    }
}
