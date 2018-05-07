package com.bobbbaich.kafka.producer;

import com.bobbbaich.kafka.model.Message;
import com.bobbbaich.kafka.producer.api.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaMessageProducer implements Producer<Message> {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Override
    public void send(String topicName, Message message) {
        kafkaTemplate.send(topicName, message);
    }
}