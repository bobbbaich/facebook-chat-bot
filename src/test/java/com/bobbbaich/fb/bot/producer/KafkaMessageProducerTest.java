package com.bobbbaich.fb.bot.producer;

import com.bobbbaich.fb.bot.model.Message;
import com.bobbbaich.fb.bot.producer.api.Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static com.bobbbaich.fb.bot.config.kafka.TopicProperties.TOPIC_ANALYSE;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaMessageProducerTest {
    @Autowired
    private KafkaEmbedded kafkaEmbedded;
    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    @Autowired
    private Producer<Message> messageProducer;

    @Before
    public void setUp() throws Exception {
        for (MessageListenerContainer container : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(container, kafkaEmbedded.getPartitionsPerTopic());
        }
    }

    @Test
    public void send() {
        Message message = Message.builder()
                .recipientId("123")
                .message("Test message by user 123")
                .build();
//      TODO: check if message has sent
        messageProducer.send(TOPIC_ANALYSE, message);
    }
}