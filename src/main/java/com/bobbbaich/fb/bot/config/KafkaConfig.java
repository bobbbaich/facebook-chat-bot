package com.bobbbaich.fb.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

//    @Autowired
//    private KafkaProducerProperties kafkaProducerProperties;
//
//    @Bean
//    public DefaultKafkaProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrap());
//        return props;
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> workUnitsKafkaTemplate() {
//        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
//        kafkaTemplate.setDefaultTopic(kafkaProducerProperties.getTopic());
//        return kafkaTemplate;
//    }
//
//    @Bean
//    public StringSerializer stringKeySerializer() {
//        return new StringSerializer();
//    }
}
