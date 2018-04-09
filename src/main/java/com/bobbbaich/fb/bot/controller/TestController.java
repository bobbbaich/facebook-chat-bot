package com.bobbbaich.fb.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/isAuth")
    public String isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.valueOf(authentication != null);
    }

    @GetMapping("/kafka/send")
    public String sendHello() {
        kafkaTemplate.send("test", "Hello Kafka!");
        return "Test message sent.";
    }
}