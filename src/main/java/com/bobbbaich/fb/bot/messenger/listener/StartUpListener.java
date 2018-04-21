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
import com.github.messenger4j.messengerprofile.persistentmenu.action.NestedCallToAction;
import com.github.messenger4j.messengerprofile.persistentmenu.action.PostbackCallToAction;
import com.github.messenger4j.messengerprofile.persistentmenu.action.UrlCallToAction;
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

    @Value("${messenger.setup.startedButton}")
    private String startedButton;
    @Value("${messenger.setup.greeting}")
    private String greeting;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        PostbackCallToAction callToActionAA = PostbackCallToAction.create("Pay Bill", "PAYBILL_PAYLOAD");
        PostbackCallToAction callToActionAB = PostbackCallToAction.create("History", "HISTORY_PAYLOAD");
        PostbackCallToAction callToActionAC = PostbackCallToAction.create("Contact Info", "CONTACT_INFO_PAYLOAD");

        NestedCallToAction callToActionA = NestedCallToAction.create("My Account",
                Arrays.asList(callToActionAA, callToActionAB, callToActionAC));

        UrlCallToAction callToActionB = null;
        try {
            callToActionB = UrlCallToAction.create("Google",
                    new URL("https://www.google.com.ua"), of(WebviewHeightRatio.FULL), empty(), empty(),
                    of(WebviewShareButtonState.HIDE));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        MessengerSettings messengerSettings = MessengerSettings
                .create(of(StartButton.create(startedButton)),
                        of(Greeting.create(greeting)),
                        of(PersistentMenu.create(true, of(Arrays.asList(callToActionA, callToActionB)))),
                        empty(),
                        empty(),
                        empty(),
                        empty());
        try {
            messenger.updateSettings(messengerSettings);
        } catch (MessengerApiException | MessengerIOException e) {
//          TODO: handle exceptions
            log.warn("Exception occurred trying set messenger settings.", e);
        }
    }
}
