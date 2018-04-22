package com.bobbbaich.fb.bot.messenger.listener;

import com.bobbbaich.fb.bot.messenger.config.MessengerProperties;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.common.WebviewHeightRatio;
import com.github.messenger4j.common.WebviewShareButtonState;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.messengerprofile.MessengerSettings;
import com.github.messenger4j.messengerprofile.getstarted.StartButton;
import com.github.messenger4j.messengerprofile.greeting.Greeting;
import com.github.messenger4j.messengerprofile.homeurl.HomeUrl;
import com.github.messenger4j.messengerprofile.persistentmenu.PersistentMenu;
import com.github.messenger4j.messengerprofile.persistentmenu.action.PostbackCallToAction;
import com.github.messenger4j.messengerprofile.persistentmenu.action.UrlCallToAction;
import com.github.messenger4j.messengerprofile.targetaudience.AllTargetAudience;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile("docker")
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    private final Messenger messenger;
    private final MessengerProperties props;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        MessengerSettings messengerSettings = MessengerSettings
                .create(of(StartButton.create(props.getGetStartedPayload())),
                        of(Greeting.create(props.getGreeting())),
                        persistentMenu(),
                        whitelistedURLs(),
                        empty(),
                        homeUrl(),
                        of(AllTargetAudience.create()));
        try {
            messenger.updateSettings(messengerSettings);
        } catch (MessengerApiException | MessengerIOException e) {
//          TODO: handle exceptions
            log.warn("Exception occurred trying set messenger settings.", e);
        }
    }

    private Optional<HomeUrl> homeUrl() {
        try {
            return of(HomeUrl.create(new URL(props.getHomeURL()), true));
        } catch (MalformedURLException e) {
            log.error("Malformed URL has occurred. Check bot settings.", e);
            return empty();
        }
    }

    private Optional<PersistentMenu> persistentMenu() {
        try {
            UrlCallToAction callToGoogle = UrlCallToAction.create("Google", new URL("https://www.google.com.ua"), of(WebviewHeightRatio.FULL), empty(), empty(), of(WebviewShareButtonState.HIDE));
            UrlCallToAction callToTwitterConnect = UrlCallToAction.create("Connect Twitter", new URL("https://streammy.tk/signin/twitter"), of(WebviewHeightRatio.FULL), empty(), empty(), of(WebviewShareButtonState.HIDE));
            PostbackCallToAction callToGetHelp = PostbackCallToAction.create("Get Help", props.getHelpPayload());

            return of(PersistentMenu.create(false, of(Arrays.asList(callToGoogle, callToTwitterConnect, callToGetHelp))));
        } catch (MalformedURLException e) {
            log.error("Malformed URL has occurred. Check bot settings.", e);
            return empty();
        }
    }

    private Optional<List<URL>> whitelistedURLs() {
        return of(props.getWhitelistedURLs().stream().map(spec -> {
            try {
                return new URL(spec);
            } catch (MalformedURLException e) {
                log.error("There is incorrect whitelisted URL: {} in messenger properties. Check your configuration.", spec);
                throw new IllegalArgumentException("Incorrect whitelisted URL in messenger properties.", e);
            }
        }).collect(Collectors.toList()));
    }
}