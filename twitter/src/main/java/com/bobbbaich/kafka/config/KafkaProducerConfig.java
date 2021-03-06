package com.bobbbaich.kafka.config;


import com.bobbbaich.kafka.model.Message;
import com.bobbbaich.kafka.model.TweetMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {
    private final KafkaProperties properties;

    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, Message> kafkaMessageTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, TweetMessage> kafkaTweetMessageTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}