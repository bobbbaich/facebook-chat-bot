package com.bobbbaich.fb.bot.config.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@RequiredArgsConstructor
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    private final KafkaProperties properties;

    @Bean
    public <T> ConsumerFactory<String, T> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}