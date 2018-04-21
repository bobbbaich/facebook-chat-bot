package com.bobbbaich.fb.bot.messenger.config;

import com.github.messenger4j.Messenger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessengerConfig {

    private static final String APP_SECRET = "${messenger4j.appSecret}";
    private static final String VERIFY_TOKEN = "${messenger4j.verifyToken}";
    private static final String PAGE_ACCESS_TOKEN = "${messenger4j.pageAccessToken}";

    /**
     * Initializes the {@code MessengerSendClient}.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
    public Messenger messengerSendClient(@Value(PAGE_ACCESS_TOKEN) String pageAccessToken,
                                         @Value(APP_SECRET) final String appSecret,
                                         @Value(VERIFY_TOKEN) final String verifyToken) {
        log.debug("Initializing Messenger - pageAccessToken: {}", pageAccessToken);
        return Messenger.create(pageAccessToken, appSecret, verifyToken);
    }
}