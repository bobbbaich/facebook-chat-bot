package com.bobbbaich.twitter.service;

import com.bobbbaich.kafka.model.Message;
import com.bobbbaich.twitter.service.api.StreamOperationsService;
import com.bobbbaich.twitter.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Stream;
import org.springframework.stereotype.Service;

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
        String topicName = "analyze";
        String keyWord = message.getMessage();
        log.debug(">>>>>>Recipient ID = {}, topic name = {}, keyWord = {}, Limit = {}", recipientId, topicName, keyWord, DEFAULT_LIMIT);
        Stream stream = streamOperationsService.runStream(recipientId, topicName, keyWord, DEFAULT_LIMIT);
        log.debug("Stream {}", stream);
    }
}
