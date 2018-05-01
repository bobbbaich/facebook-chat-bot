package com.bobbbaich.fb.bot.messenger.service;

import com.bobbbaich.fb.bot.cache.api.Event;
import com.bobbbaich.fb.bot.messenger.service.annotaion.EventQualifier;
import com.bobbbaich.fb.bot.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@EventQualifier
@Component
public class EventMessageProvider implements MessageProvider<Message> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void send2Analyse(Message obj) {
        applicationEventPublisher.publishEvent(AnalyseEvent.supply(this, obj));
        log.debug("Publish message for running analyse");
    }

    private static class AnalyseEvent extends Event<Message> {

        private AnalyseEvent(Object source, Message eventObj) {
            super(source, eventObj);
        }

        private static Event<Message> supply(Object source, Message obj) {
            return new AnalyseEvent(source, obj);
        }
    }
}
