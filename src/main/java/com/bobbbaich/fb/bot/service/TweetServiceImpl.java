package com.bobbbaich.fb.bot.service;


import com.bobbbaich.fb.bot.kafka.api.StreamOperationsService;
import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.service.api.TweetService;
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
        String topicName = message.getTopic();
        String keyWord = message.getMessage();
        int limit = message.getLimit() != null ? message.getLimit() : DEFAULT_LIMIT;
        log.debug(">>>>>>Recipient ID = {}, topic name = {}, keyWord = {}, Limit = {}", recipientId, topicName, keyWord, limit);
        Stream stream = streamOperationsService.runStream(recipientId, topicName, keyWord, limit);
        log.debug("Stream {}", stream);
    }
}
