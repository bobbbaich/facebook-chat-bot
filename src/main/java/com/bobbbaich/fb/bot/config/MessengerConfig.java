package com.bobbbaich.fb.bot.config;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MessengerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MessengerConfig.class);

    private static final String APP_SECRET = "${messenger4j.appSecret}";
    private static final String VERIFY_TOKEN = "${messenger4j.verifyToken}";
    private static final String LOCAL_PAGE_ACCESS_TOKEN = "${messenger4j.local.pageAccessToken}";
    private static final String PAGE_ACCESS_TOKEN = "${messenger4j.pageAccessToken}";
    private static final String LOCAL_APP_SECRET = "${messenger4j.local.appSecret}";
    private static final String LOCAL_VERIFY_TOKEN = "${messenger4j.local.verifyToken}";

    private TextMessageEventHandler textMessageEventHandler;

    /**
     * Initializes the {@code MessengerSendClient}.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
    @Profile("REMOTE")
    public MessengerSendClient messengerSendClient(@Value(PAGE_ACCESS_TOKEN) String pageAccessToken) {
        LOG.debug("Initializing MessengerSendClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
    }

    /**
     * Initializes the {@code MessengerReceiveClient}.
     *
     * @param appSecret   the {@code Application Secret}
     * @param verifyToken the {@code Verification Token} that has been provided by you during the setup of the {@code
     *                    Webhook}
     */

    @Bean
    @Profile("REMOTE")
    public MessengerReceiveClient messengerReceiveClient(@Value(APP_SECRET) final String appSecret,
                                                         @Value(VERIFY_TOKEN) final String verifyToken) {
        LOG.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", appSecret, verifyToken);
        return MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
                .onTextMessageEvent(textMessageEventHandler)
                .build();
    }

    /**
     * Initializes the {@code MessengerSendClient} for profile 'LOCAL'.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
    @Profile("LOCAL")
    public MessengerSendClient messengerSendClientDev(@Value(LOCAL_PAGE_ACCESS_TOKEN) String pageAccessToken) {
        LOG.debug("Initializing MessengerSendClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
    }

    /**
     * Initializes the {@code MessengerReceiveClient} for profile 'LOCAL'.
     *
     * @param appSecret   the {@code Application Secret}
     * @param verifyToken the {@code Verification Token} that has been provided by you during the setup of the {@code
     *                    Webhook}
     */

    @Bean
    @Profile("LOCAL")
    public MessengerReceiveClient messengerReceiveClientDev(@Value(LOCAL_APP_SECRET) final String appSecret,
                                                            @Value(LOCAL_VERIFY_TOKEN) final String verifyToken) {
        LOG.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", appSecret, verifyToken);
        return MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
                .onTextMessageEvent(textMessageEventHandler)
                .build();
    }

    @Autowired
    public void setTextMessageEventHandler(TextMessageEventHandler textMessageEventHandler) {
        this.textMessageEventHandler = textMessageEventHandler;
    }
}