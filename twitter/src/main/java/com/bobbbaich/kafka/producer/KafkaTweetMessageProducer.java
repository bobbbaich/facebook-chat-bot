package com.bobbbaich.kafka.producer;

import com.bobbbaich.kafka.model.TweetMessage;
import com.bobbbaich.kafka.producer.api.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaTweetMessageProducer implements Producer<TweetMessage> {
    private final KafkaTemplate<String, TweetMessage> kafkaTemplate;

    @Override
    public void send(String topicName, TweetMessage message) {
        kafkaTemplate.send(topicName, message);
    }
}
