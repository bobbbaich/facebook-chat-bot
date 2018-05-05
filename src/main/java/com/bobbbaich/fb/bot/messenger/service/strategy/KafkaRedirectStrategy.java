package com.bobbbaich.fb.bot.messenger.service.strategy;

import com.bobbbaich.fb.bot.messenger.service.annotaion.KafkaQualifier;
import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.producer.api.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.bobbbaich.fb.bot.config.kafka.TopicProperties.TOPIC_ANALYSE;

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