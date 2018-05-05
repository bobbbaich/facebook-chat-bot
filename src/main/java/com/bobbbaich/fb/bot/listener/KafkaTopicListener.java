package com.bobbbaich.fb.bot.listener;

import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.service.api.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.bobbbaich.fb.bot.config.kafka.TopicProperties.TOPIC_ANALYSE;

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
}