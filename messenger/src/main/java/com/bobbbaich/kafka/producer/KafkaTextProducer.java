package com.bobbbaich.kafka.producer;

import com.bobbbaich.kafka.producer.api.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaTextProducer implements Producer<String> {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String topicName, String value) {
        kafkaTemplate.send(topicName, value);
    }
}