package com.bobbbaich.fb.bot.messenger.handler;

import com.bobbbaich.fb.bot.service.UserService;
import com.github.messenger4j.receive.events.PostbackEvent;
import com.github.messenger4j.receive.handlers.PostbackEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostbackHandler implements PostbackEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(PostbackHandler.class);

    private UserService userService;

    @Override
    public void handle(PostbackEvent event) {
        String postbackPayload = event.getPayload();
        LOG.debug("Postback event payload: {}", postbackPayload);

        switch (postbackPayload) {
            case "Get Started":
                String newUserId = event.getSender().getId();
                userService.create(newUserId);
                break;
            default:
                break;
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
