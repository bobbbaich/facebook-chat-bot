package com.bobbbaich.fb.bot.messenger.service;

import com.bobbbaich.fb.bot.messenger.service.annotaion.KafkaQualifier;
import com.bobbbaich.fb.bot.messenger.service.api.MessageService;
import com.bobbbaich.fb.bot.messenger.service.strategy.MessageRedirectStrategy;
import com.bobbbaich.fb.bot.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TextMessageService implements MessageService {
    private final MessageRedirectStrategy<Message> messageRedirectStrategy;

    public TextMessageService(@KafkaQualifier MessageRedirectStrategy<Message> messageRedirectStrategy) {
        this.messageRedirectStrategy = messageRedirectStrategy;
    }

    @Override
    public void sendToAnalysis(String recipientId, String text) {
        Message message = Message.builder()
                .recipientId(recipientId)
                .message(text)
                .build();

        messageRedirectStrategy.redirect(message);
        log.debug("Message was sent to analyse = {}", message);
    }
}