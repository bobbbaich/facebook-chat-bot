package com.bobbbaich.fb.bot.messenger.controller;

import com.bobbbaich.fb.bot.messenger.config.MessengerProperties;
import com.bobbbaich.fb.bot.service.api.AnalyseService;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.MessagingType;
import com.github.messenger4j.send.message.TemplateMessage;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.message.template.ButtonTemplate;
import com.github.messenger4j.send.message.template.button.Button;
import com.github.messenger4j.send.message.template.button.PostbackButton;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.webhook.Event;
import com.github.messenger4j.webhook.event.PostbackEvent;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessengerEventHandler {
    private final Messenger messenger;
    private final MessengerProperties props;
    private final AnalyseService analyseService;

    public void onEvent(Event event) {
        final IdRecipient idRecipient = IdRecipient.create(event.senderId());
        log.debug("idRecipient: {}", idRecipient);

        if (event.isTextMessageEvent()) {
            log.debug("Text Message event received.");
            TextMessageEvent textMessageEvent = event.asTextMessageEvent();
            log.debug("Message has been received with text: {}", textMessageEvent.text());
            analyseService.analyse(textMessageEvent.text());
        } else if (event.isPostbackEvent()) {
            PostbackEvent postbackEvent = event.asPostbackEvent();

            postbackEvent.payload()
                    .filter(p -> p.equals(props.getGetStartedPayload()))
                    .ifPresent(p -> send(idRecipient));

            postbackEvent.payload()
                    .filter(p -> p.equals(props.getStartAnalysisPayload()))
                    .ifPresent(p -> button(idRecipient));
        }
    }

    private void send(IdRecipient idRecipient) {
        try {
            messenger.send(MessagePayload.create(idRecipient, MessagingType.RESPONSE, TextMessage.create("Hi Hello!")));
        } catch (MessengerApiException | MessengerIOException e) {
            e.printStackTrace();
        }
    }

    private void button(IdRecipient idRecipient) {
        try {
            PostbackButton buttonB = PostbackButton.create("Start Analysing", "START_ANALYSIS_PAYLOAD");

            List<Button> buttons = Collections.singletonList(buttonB);
            ButtonTemplate buttonTemplate = ButtonTemplate.create("What do you want to do next?", buttons);

            TemplateMessage templateMessage = TemplateMessage.create(buttonTemplate);
            MessagePayload payload = MessagePayload.create(idRecipient, MessagingType.RESPONSE, templateMessage);

            messenger.send(payload);
        } catch (MessengerIOException | MessengerApiException e) {
            e.printStackTrace();
        }
    }
}