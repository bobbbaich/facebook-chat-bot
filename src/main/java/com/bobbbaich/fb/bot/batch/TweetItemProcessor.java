//package com.bobbbaich.fb.bot.batch;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.social.twitter.api.Tweet;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TweetItemProcessor implements ItemProcessor<Tweet, Tweet> {
//    private static final Logger LOG = LoggerFactory.getLogger(TweetItemProcessor.class);
//
//    @Override
//    public Tweet process(Tweet tweet) throws Exception {
//        LOG.debug("process(tweet with id: {})", tweet.getId());
//        return tweet;
//    }
//}
