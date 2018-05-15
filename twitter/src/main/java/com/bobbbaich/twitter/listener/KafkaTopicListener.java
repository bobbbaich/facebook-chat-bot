package com.bobbbaich.twitter.listener;

import com.bobbbaich.kafka.model.Message;
import com.bobbbaich.kafka.model.TweetMessage;
import com.bobbbaich.twitter.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.bobbbaich.kafka.config.TopicProperties.TOPIC_ANALYSE;
import static com.bobbbaich.kafka.config.TopicProperties.TOPIC_TWEET;


@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaTopicListener {
    private final TweetService tweetService;

    @KafkaListener(topics = TOPIC_ANALYSE)
    public void startAnalysis(Message message) {
        log.debug("=====> Received Message for analise: {}", message);
        tweetService.collectTweets(message);
    }

    @KafkaListener(topics = TOPIC_TWEET)
    public void tweetMessage(TweetMessage message) {
        log.debug("++++++> Received TweetMessage for analise: {}", message);
    }
}