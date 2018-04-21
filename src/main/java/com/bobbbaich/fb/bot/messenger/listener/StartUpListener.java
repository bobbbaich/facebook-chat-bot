package com.bobbbaich.fb.bot.messenger.listener;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.messengerprofile.MessengerSettings;
import com.github.messenger4j.messengerprofile.getstarted.StartButton;
import com.github.messenger4j.messengerprofile.greeting.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile("docker")
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    private final Messenger messenger;

    @Value("${messenger.setup.startedButton}")
    private String startedButton;
    @Value("${messenger.setup.greeting}")
    private String greeting;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        MessengerSettings messengerSettings = MessengerSettings
                .create(Optional.of(StartButton.create(startedButton)),
                        Optional.of(Greeting.create(greeting)),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty());
        try {
            messenger.updateSettings(messengerSettings);
        } catch (MessengerApiException | MessengerIOException e) {
//          TODO: handle exceptions
            log.warn("Exception occurred trying set messenger settings.", e);
        }
    }
}
