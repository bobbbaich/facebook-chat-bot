package com.bobbbaich.fb.bot.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

//    @KafkaListener(topics = "${kafka.topic.helloworld}")
//    public void receive(String payload) {
//        log.debug("received payload='{}'", payload);
//        latch.countDown();
//    }

    @KafkaListener(topics = "${kafka.topic.twitter}")
    public void receiveTweet(ConsumerRecord<?, ?> tweet) {
        log.debug("<<<<<<<<<<<<<<<<<- Received Tweet='{}'", tweet.toString());
    }
}