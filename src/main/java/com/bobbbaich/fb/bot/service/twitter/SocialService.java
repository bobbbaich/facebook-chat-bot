package com.bobbbaich.fb.bot.service.twitter;

import com.bobbbaich.fb.bot.service.twitter.listener.TwitterStreamListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SocialService {
    private static final Logger LOG = LoggerFactory.getLogger(SocialService.class);

    private StreamingOperations streamOperations;
    private TwitterStreamListener twitterStreamListener;
    private FilterStreamParameters params = new FilterStreamParameters();
    private List<StreamListener> listeners = new LinkedList<>();

    public void runStream() {
        LOG.debug("FacebookService runStream()");
        listeners.add(twitterStreamListener);
        params.track("test");
        streamOperations.filter(params, listeners);
    }

    public void openStream() {
    }

    public void closeStream() {

    }

    @Autowired
    public void setStreamingOperations(StreamingOperations streamOperations) {
        this.streamOperations = streamOperations;
    }

    @Autowired
    public void setTwitterStreamListener(TwitterStreamListener twitterStreamListener) {
        this.twitterStreamListener = twitterStreamListener;
    }
}
