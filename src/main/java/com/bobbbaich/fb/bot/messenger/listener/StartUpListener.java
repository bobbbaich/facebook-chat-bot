package com.bobbbaich.fb.bot.messenger.listener;

import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.setup.MessengerSetupClient;
import com.github.messenger4j.setup.SetupResponse;
import com.github.messenger4j.user.UserProfile;
import com.github.messenger4j.user.UserProfileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(StartUpListener.class);

    @Value("${messenger.setup.getStartedButton}")
    private String GET_STARTED_BUTTON;
    @Value("${messenger.setup.greeting}")
    private String GREETING;

    private MessengerSetupClient setupClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            setGetStartedButton();
            LOG.debug("Get Started Button set successfully");
            setGreeting();
            LOG.debug("Greeting Text set successfully");
        } catch (MessengerApiException | MessengerIOException e) {
            LOG.error("Page setting up failed", e);
        }
    }

    private void setGetStartedButton() throws MessengerApiException, MessengerIOException {
        SetupResponse setupResponse = setupClient.setupStartButton(GET_STARTED_BUTTON);
        LOG.debug("Get Started Button setup status: ", setupResponse.getResult());
    }

    private void setGreeting() throws MessengerApiException, MessengerIOException {
        SetupResponse setupResponse = setupClient.setupWelcomeMessage(GREETING);
        LOG.debug("Greeting setup status: ", setupResponse.getResult());
    }

    @Autowired
    public void setSendClient(@Lazy MessengerSetupClient setupClient) {
        this.setupClient = setupClient;
    }
}
