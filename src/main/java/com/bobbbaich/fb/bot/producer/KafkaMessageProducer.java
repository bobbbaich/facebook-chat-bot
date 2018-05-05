package com.bobbbaich.fb.bot.producer;

import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.producer.api.Producer;
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