package com.bobbbaich.messenger.service.strategy;

import com.bobbbaich.messenger.model.Message;
import com.bobbbaich.messenger.producer.api.Producer;
import com.bobbbaich.messenger.service.annotaion.KafkaQualifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.bobbbaich.messenger.config.kafka.TopicProperties.TOPIC_ANALYSE;

@Slf4j
@RequiredArgsConstructor
@KafkaQualifier
@Component
public class KafkaRedirectStrategy implements MessageRedirectStrategy<Message> {
    private final Producer<Message> producer;

    @Override
    public void redirect(Message message) {
        producer.send(TOPIC_ANALYSE, message);
        log.debug("Send message in kafka topic = {}", TOPIC_ANALYSE);
    }
}