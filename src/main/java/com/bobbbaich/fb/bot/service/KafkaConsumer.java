package com.bobbbaich.fb.bot.service;

import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.model.TweetMessage;
import com.bobbbaich.fb.bot.service.api.TweetService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {
    private final TweetService tweetService;
    private Gson mapper = new Gson();

    @KafkaListener(topics = "test")
    public void listen(String json) {
        log.debug("-----> Received Message in group foo: {}", mapper.fromJson(json, TweetMessage.class));
    }

    @KafkaListener(topics = "analyse")
    public void startAnalyse(String json) {
        Message message = mapper.fromJson(json, Message.class);
        tweetService.collectTweets(message);
        log.debug("=====> Received Message for analise: {}", message);
    }
}
