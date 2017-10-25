package com.bobbbaich.fb.bot.service.twitter.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;


@Component
public class TwitterStreamListener extends DefaultStreamListener {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterStreamListener.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onTweet(Tweet tweet) {
        String json;
        try {
            json = mapper.writeValueAsString(tweet);
            LOG.debug(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
