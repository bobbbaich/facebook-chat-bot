package com.bobbbaich.fb.bot.messenger.listener;

import com.bobbbaich.fb.bot.messenger.config.MessengerProperty;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.messengerprofile.MessengerSettings;
import com.github.messenger4j.messengerprofile.SetupResponse;
import com.github.messenger4j.messengerprofile.getstarted.StartButton;
import com.github.messenger4j.messengerprofile.greeting.Greeting;
import com.github.messenger4j.messengerprofile.homeurl.HomeUrl;
import com.github.messenger4j.messengerprofile.persistentmenu.PersistentMenu;
import com.github.messenger4j.messengerprofile.persistentmenu.action.PostbackCallToAction;
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

import static com.bobbbaich.fb.bot.messenger.config.PostbackPayload.*;
import static com.github.messenger4j.messengerprofile.MessengerSettingProperty.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile("docker")
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    private final Messenger messenger;
    private final MessengerProperty props;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        MessengerSettings messengerSettings = MessengerSettings
                .create(of(StartButton.create(GET_STARTED_PAYLOAD)),
                        of(Greeting.create(props.getGreeting())),
                        persistentMenu(),
                        empty(),
                        empty(),
                        empty(),
                        of(AllTargetAudience.create()));
        try {
            SetupResponse deleteResponse = messenger.deleteSettings(GREETING, ACCOUNT_LINKING_URL, HOME_URL, WHITELISTED_DOMAINS, TARGET_AUDIENCE, START_BUTTON, PERSISTENT_MENU);
            SetupResponse updateResponse = messenger.updateSettings(messengerSettings);
            log.info("Messenger settings updated with following results. Delete settings: {}; Update settings: {}", deleteResponse.result(), updateResponse.result());
        } catch (MessengerApiException | MessengerIOException e) {
//          TODO: handle exceptions
            log.warn("Exception occurred trying set messenger settings. Messenger Bot may work incorrect.", e);
        }
    }

    private Optional<PersistentMenu> persistentMenu() {
        PostbackCallToAction callToAnalyse = PostbackCallToAction.create("Start Analysis", START_ANALYSIS_PAYLOAD);
        PostbackCallToAction callToGetHelp = PostbackCallToAction.create("Get Help", HELP_PAYLOAD);

        return of(PersistentMenu.create(false, of(Arrays.asList(callToAnalyse, callToGetHelp))));
    }

    private Optional<HomeUrl> homeUrl() {
        try {
            return of(HomeUrl.create(new URL(props.getHomeURL()), props.isInTest()));
        } catch (MalformedURLException e) {
            log.error("Malformed URL has occurred. Messenger Bot may work incorrect.", e);
            return empty();
        }
    }

    private Optional<List<URL>> whitelistedURLs() {
        return of(props.getWhitelistedURLs().stream().map(spec -> {
            try {
                return new URL(spec);
            } catch (MalformedURLException e) {
                log.error("There is incorrect whitelisted URL: {} in messenger properties. Check your configuration.", spec);
                throw new IllegalArgumentException("Incorrect whitelisted URL in messenger properties. Messenger Bot may work incorrect.", e);
            }
        }).collect(Collectors.toList()));
    }
}