package com.bobbbaich.fb.bot.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;

public class TwitterItemWriter implements ItemStreamWriter<Tweet> {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterItemWriter.class);

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

    @Override
    public void write(List<? extends Tweet> list) throws Exception {
        LOG.debug("write");
    }
}
