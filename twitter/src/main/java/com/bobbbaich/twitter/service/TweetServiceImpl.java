package com.bobbbaich.twitter.service;

import com.bobbbaich.kafka.model.Message;
import com.bobbbaich.twitter.service.api.StreamOperationsService;
import com.bobbbaich.twitter.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Service;

import static com.bobbbaich.kafka.config.TopicProperties.TOPIC_TWEET;

@Slf4j
@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final StreamOperationsService streamOperationsService;
    private final static Integer DEFAULT_LIMIT = 100;

    @Override
    public void collectTweets(Message message) {
        String recipientId = message.getRecipientId();
//        TODO: change topic definition strategy
        String keyWord = message.getMessage();
        log.debug(">>>>>>Recipient ID = {}, topic name = {}, keyWord = {}, Limit = {}", recipientId, TOPIC_TWEET, keyWord, DEFAULT_LIMIT);
        Stream stream = streamOperationsService.runStream(recipientId, TOPIC_TWEET, keyWord, DEFAULT_LIMIT);
        log.debug("Stream {}", stream);
    }
}
