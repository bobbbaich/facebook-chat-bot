package com.bobbbaich.fb.bot.service.twitter;

import com.bobbbaich.fb.bot.service.twitter.listener.TwitterStreamListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SocialService {
    private static final Logger LOG = LoggerFactory.getLogger(SocialService.class);

    private StreamingOperations streamOperations;

    @Autowired
    private TwitterStreamListener twitterStreamListener;

    public void runStream() {
        LOG.debug("FacebookService runStream()");
        FilterStreamParameters params = new FilterStreamParameters();
        List<StreamListener> listeners = new LinkedList<>();

        listeners.add(twitterStreamListener);
        params.track("test");
        streamOperations.filter(params, listeners);
    }

    private Stream streamFilter;

    public void openStream() {
        LOG.debug("FacebookService openStream()");
        streamFilter.open();
    }

    public void closeStream() {
        LOG.debug("FacebookService closeStream()");
        streamFilter.close();
    }


    @Autowired
    public void setTwitter(StreamingOperations streamOperations) {
        this.streamOperations = streamOperations;
    }

    @Autowired
    public void setTwitterStreamListener(TwitterStreamListener twitterStreamListener) {
        this.twitterStreamListener = twitterStreamListener;
    }
}
