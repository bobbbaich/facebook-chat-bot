package com.bobbbaich.fb.bot.messenger.service;

import com.bobbbaich.fb.bot.messenger.service.annotaion.EventQualifier;
import com.bobbbaich.fb.bot.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageAnalyseService implements AnalyseService {

    private final MessageProvider<Message> messageProvider;

    public MessageAnalyseService(@EventQualifier MessageProvider<Message> messageProvider) {
        this.messageProvider = messageProvider;
    }

    private final static String DEFAULT_TOPIC = "test";

    @Override
    public void startAnalyse(String recipientId, String text) {
        Message message = Message.builder().recipientId(recipientId).message(text).topic(DEFAULT_TOPIC).build();
        messageProvider.send2Analyse(message);
        log.debug("Message was sent to analyse = {}", message);
    }
}
