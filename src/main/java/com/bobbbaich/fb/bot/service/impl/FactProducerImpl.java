package com.bobbbaich.fb.bot.service.impl;

import com.bobbbaich.fb.bot.service.FactProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FactProducerImpl implements FactProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void produce() {
        kafkaTemplate.send("TextLinesTopic", "hello world!");
    }

    @KafkaListener(topics = "WordsWithCountsTopic")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
