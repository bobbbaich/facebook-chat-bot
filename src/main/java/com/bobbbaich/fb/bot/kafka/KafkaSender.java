//package com.bobbbaich.fb.bot.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class KafkaSender {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    private String kafkaTopic = "topic";
//
//
//    public void send(String message) {
//
//        kafkaTemplate.send(kafkaTopic, message);
//    }
//}