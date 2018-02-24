package com.bobbbaich.fb.bot.controller;

import com.bobbbaich.fb.bot.service.FactProducer;
import com.bobbbaich.fb.bot.service.KafkaService;
import com.bobbbaich.fb.bot.service.TwitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TwitterService service;
    private final KafkaService kService;
    private final FactProducer factProducer;

    @GetMapping("/isAuth")
    public String isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.valueOf(authentication != null);
    }

    @GetMapping("/run")
    public String run() {
        service.run();
        return "Twitter stream running...";
    }


    @GetMapping("/kafka/stream")
    public String kafkaStream() {
        kService.run();
        return "Kafka stream running...";
    }

    @GetMapping("/kafka/produce")
    public String kafkaProduce() {
        factProducer.produce();
        return "Kafka producing...";
    }
}