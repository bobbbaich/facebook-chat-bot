package com.bobbbaich.fb.bot.messenger.handler;

import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.receive.events.PostbackEvent;
import com.github.messenger4j.receive.handlers.PostbackEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class PostbackHandler implements PostbackEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(PostbackHandler.class);

    private MessengerSendClient sendClient;

    @Override
    public void handle(PostbackEvent event) {
        String postbackPayload = event.getPayload();
        LOG.debug("Postback event payload: {}", postbackPayload);

        final Recipient recipient = Recipient.newBuilder().recipientId(event.getSender().getId()).build();
        final NotificationType notificationType = NotificationType.REGULAR;
        final String metadata = "DEVELOPER_DEFINED_METADATA";

        try {
            sendClient.sendTextMessage(recipient, NotificationType.REGULAR, "Hop Hey LALALEY");
        } catch (MessengerApiException e) {
            e.printStackTrace();
        } catch (MessengerIOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setSendClient(@Lazy MessengerSendClient sendClient) {
        this.sendClient = sendClient;
    }
}
