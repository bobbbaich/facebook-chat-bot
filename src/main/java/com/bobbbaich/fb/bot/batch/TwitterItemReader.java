package com.bobbbaich.fb.bot.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

@Component
public class TwitterItemReader implements ItemStreamReader<Tweet> {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterItemReader.class);


    @Override
    public Tweet read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        LOG.debug("read");

        return null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        LOG.debug("open");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        LOG.debug("update");
    }

    @Override
    public void close() throws ItemStreamException {
        LOG.debug("close");
    }
}
