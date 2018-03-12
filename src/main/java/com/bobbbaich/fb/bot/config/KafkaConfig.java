package com.bobbbaich.fb.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Slf4j
@Configuration
//@EnableKafka
//@EnableKafkaStreams
public class KafkaConfig {
//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    public StreamsConfig streamsConfig(KafkaProperties properties) {
//        Map<String, String> props = properties.getProperties();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "default-app-id");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        return new StreamsConfig(props);
//    }

//    @Bean
//    public KafkaTemplate kafkaTemplate(ProducerFactory producerFactory) {
//        return new KafkaTemplate(producerFactory);
//    }

}