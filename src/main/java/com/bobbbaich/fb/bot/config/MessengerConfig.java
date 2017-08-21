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

    private TextMessageEventHandler textMessageEventHandler;

    /**
     * Initializes the {@code MessengerSendClient}.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
    @Profile("REMOTE")
    public MessengerSendClient messengerSendClient(@Value("${messenger4j.pageAccessToken}") String pageAccessToken) {
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
    public MessengerReceiveClient messengerReceiveClient(@Value("${messenger4j.appSecret}") final String appSecret,
                                                         @Value("${messenger4j.verifyToken}") final String verifyToken) {
        LOG.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", appSecret, verifyToken);
        return MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
                .onTextMessageEvent(textMessageEventHandler)
                .build();
    }

    /**
     * Initializes the {@code MessengerSendClient} for profile 'local'.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
    @Profile("LOCAL")
    public MessengerSendClient messengerSendClientDev(@Value("${messenger4j.local.pageAccessToken}") String pageAccessToken) {
        LOG.debug("Initializing MessengerSendClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
    }

    /**
     * Initializes the {@code MessengerReceiveClient} for profile 'local'.
     *
     * @param appSecret   the {@code Application Secret}
     * @param verifyToken the {@code Verification Token} that has been provided by you during the setup of the {@code
     *                    Webhook}
     */

    @Bean
    @Profile("LOCAL")
    public MessengerReceiveClient messengerReceiveClientDev(@Value("${messenger4j.local.appSecret}") final String appSecret,
                                                            @Value("${messenger4j.local.verifyToken}") final String verifyToken) {
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