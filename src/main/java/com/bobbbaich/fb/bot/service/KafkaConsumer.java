package com.bobbbaich.fb.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void listen(String message) {
        log.debug("-----> Received Message in group foo: {}", message);
    }
}
