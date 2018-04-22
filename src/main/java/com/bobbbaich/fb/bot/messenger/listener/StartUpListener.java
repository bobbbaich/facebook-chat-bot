package com.bobbbaich.fb.bot.messenger.listener;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.common.WebviewHeightRatio;
import com.github.messenger4j.common.WebviewShareButtonState;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.messengerprofile.MessengerSettings;
import com.github.messenger4j.messengerprofile.getstarted.StartButton;
import com.github.messenger4j.messengerprofile.greeting.Greeting;
import com.github.messenger4j.messengerprofile.persistentmenu.PersistentMenu;
import com.github.messenger4j.messengerprofile.persistentmenu.action.PostbackCallToAction;
import com.github.messenger4j.messengerprofile.persistentmenu.action.UrlCallToAction;
import com.github.messenger4j.messengerprofile.targetaudience.AllTargetAudience;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile("docker")
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    private final Messenger messenger;

    @Value("${messenger.setup.getStartedPayload}")
    private String getStartedPayload;
    @Value("${messenger.setup.greeting}")
    private String greeting;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            MessengerSettings messengerSettings = MessengerSettings
                    .create(of(StartButton.create(getStartedPayload)),
                            of(Greeting.create(greeting)),
                            of(persistentMenu()),
                            empty(),
                            empty(),
                            empty(),
                            of(AllTargetAudience.create()));

            messenger.updateSettings(messengerSettings);
        } catch (MessengerApiException | MessengerIOException e) {
//          TODO: handle exceptions
            log.warn("Exception occurred trying set messenger settings.", e);
        }
    }

    private PersistentMenu persistentMenu() throws MessengerIOException {
        try {
            UrlCallToAction callToGoogle = UrlCallToAction.create("Google", new URL("https://www.google.com.ua"), of(WebviewHeightRatio.FULL), empty(), empty(), of(WebviewShareButtonState.HIDE));
            UrlCallToAction callToTwitterConnect = UrlCallToAction.create("Connect Twitter", new URL("https://streammy.tk/signin/twitter"), of(WebviewHeightRatio.FULL), empty(), empty(), of(WebviewShareButtonState.HIDE));
            PostbackCallToAction callToGetHelp = PostbackCallToAction.create("Get Help", "GET_HELP_PAYLOAD");

            return PersistentMenu.create(false, of(Arrays.asList(callToGoogle, callToTwitterConnect, callToGetHelp)));
        } catch (MalformedURLException e) {
            log.error("Malformed URL has occurred. Check bot settings.", e);
            throw new MessengerIOException(e);
        }
    }
}