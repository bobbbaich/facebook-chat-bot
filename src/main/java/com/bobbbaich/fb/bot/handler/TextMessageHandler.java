package com.bobbbaich.fb.bot.handler;

import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.receive.events.TextMessageEvent;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TextMessageHandler implements TextMessageEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TextMessageHandler.class);

    private final MessengerSendClient sendClient;

    @Autowired
    public TextMessageHandler(MessengerSendClient sendClient) {
        this.sendClient = sendClient;
    }

    @Override
    public void handle(TextMessageEvent event) {
        LOG.debug("Received TextMessageEvent: {}", event);

        final String messageId = event.getMid();
        final String messageText = event.getText();
        final String senderId = event.getSender().getId();
        final Date timestamp = event.getTimestamp();

        LOG.info("Received message '{}' with text '{}' from user '{}' at '{}'",
                messageId, messageText, senderId, timestamp);

        switch (messageText.toLowerCase()) {
            default:
                sendTextMessage(senderId, messageText);
        }
    }

    private void sendTextMessage(String recipientId, String text) {
        try {
            final Recipient recipient = Recipient.newBuilder().recipientId(recipientId).build();
            final NotificationType notificationType = NotificationType.REGULAR;
            final String metadata = "DEVELOPER_DEFINED_METADATA";

            this.sendClient.sendTextMessage(recipient, notificationType, text, metadata);
        } catch (MessengerApiException | MessengerIOException e) {
            handleSendException(e);
        }
    }

    private void handleSendException(Exception e) {
        LOG.error("Message could not be sent. An unexpected error occurred.", e);
    }
}
