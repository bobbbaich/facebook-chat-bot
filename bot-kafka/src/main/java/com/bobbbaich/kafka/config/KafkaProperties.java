package com.bobbbaich.kafka.config;

import lombok.Data;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
    private static final String LOG_DIR = "log.dir";

    private String logDir;
    @NotNull
    private String groupId;
    private String bootstrapServers;

    private String keySerializer;
    private String valueSerializer;

    private String keyDeserializer;
    private String valueDeserializer;
    private String trustedPackages;

    private Map<String, Object> buildCommonProperties() {
        Map<String, Object> commonProperties = new HashMap<>();

        commonProperties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        commonProperties.put(LOG_DIR, logDir);

        return commonProperties;
    }


    public Map<String, Object> buildProducerProperties() {
        Map<String, Object> producerProperties = buildCommonProperties();

        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return producerProperties;
    }

    public Map<String, Object> buildConsumerProperties() {
        Map<String, Object> consumerProperties = buildCommonProperties();

        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);

        consumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES, trustedPackages);

        return consumerProperties;
    }
}