package com.bobbbaich.fb.bot.messenger.service;

import com.bobbbaich.fb.bot.kafka.api.Producer;
import com.bobbbaich.fb.bot.messenger.service.annotaion.KafkaQualifier;
import com.bobbbaich.fb.bot.model.Message;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@KafkaQualifier
@Component
public class KafkaMessageProvider implements MessageProvider<Message> {
    private final Producer<String> producer;
    @Value("${kafka.topic.analyse:analyse}")
    private String kafkaAnalyseTopic;
    private Gson mapper = new Gson();

    @Override
    public void send2Analyse(Message message) {
        producer.send(kafkaAnalyseTopic, mapper.toJson(message));
        log.debug("Send message in kafka topic = {}", kafkaAnalyseTopic);
    }
}
