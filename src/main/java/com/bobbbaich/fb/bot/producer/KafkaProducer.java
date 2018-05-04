package com.bobbbaich.fb.bot.producer;

import com.bobbbaich.fb.bot.producer.api.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer implements Producer<String> {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }
}
