package com.bobbbaich.fb.bot.service.twitter;

import com.bobbbaich.fb.bot.dao.BatchDao;
import com.bobbbaich.fb.bot.model.Opinion;
import com.bobbbaich.fb.bot.service.twitter.events.ItemsCollectedEvent;
import com.bobbbaich.fb.bot.service.twitter.listener.TwitterStreamListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class SocialService {
    private static final Logger LOG = LoggerFactory.getLogger(SocialService.class);

    private StreamingOperations streamOperations;
    private TwitterStreamListener twitterStreamListener;
    private FilterStreamParameters params = new FilterStreamParameters();
    private List<StreamListener> listeners = new LinkedList<>();
    private Stream filter;
    private BatchDao<Opinion, Long> opinionBatchDao;

    public void runStream() {
        LOG.debug("FacebookService runStream()");
        listeners.add(twitterStreamListener);
        params.track("test");

        this.filter = streamOperations.filter(params, listeners);
    }

    public void openStream() {
    }

    @SuppressWarnings("unchecked")
    @EventListener
    public void closeStream(ItemsCollectedEvent event) {
        if (filter != null) filter.close();
        Set<Opinion> source = (Set<Opinion>) event.getSource();

        opinionBatchDao.insertBatch(source);
    }

    @Autowired
    public void setStreamingOperations(StreamingOperations streamOperations) {
        this.streamOperations = streamOperations;
    }

    @Autowired
    public void setTwitterStreamListener(TwitterStreamListener twitterStreamListener) {
        this.twitterStreamListener = twitterStreamListener;
    }

    @Autowired
    public void setOpinionBatchDao(BatchDao<Opinion, Long> opinionBatchDao) {
        this.opinionBatchDao = opinionBatchDao;
    }
}
