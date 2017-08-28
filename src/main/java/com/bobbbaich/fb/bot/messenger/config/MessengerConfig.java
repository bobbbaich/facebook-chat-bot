package com.bobbbaich.fb.bot.messenger.config;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.receive.handlers.PostbackEventHandler;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.setup.MessengerSetupClient;
import com.github.messenger4j.user.UserProfileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MessengerConfig.class);

    private static final String APP_SECRET = "${messenger4j.appSecret}";
    private static final String VERIFY_TOKEN = "${messenger4j.verifyToken}";
    private static final String PAGE_ACCESS_TOKEN = "${messenger4j.pageAccessToken}";

    private TextMessageEventHandler textMessageEventHandler;
    private PostbackEventHandler postbackEventHandler;

    /**
     * Initializes the {@code MessengerSendClient}.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
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
    public MessengerReceiveClient messengerReceiveClient(@Value(APP_SECRET) final String appSecret,
                                                         @Value(VERIFY_TOKEN) final String verifyToken) {
        LOG.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", appSecret, verifyToken);
        return MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
                .onTextMessageEvent(textMessageEventHandler)
                .onPostbackEvent(postbackEventHandler)
                .build();
    }

    @Bean
    public MessengerSetupClient messengerSetupClient(@Value(PAGE_ACCESS_TOKEN) String pageAccessToken) {
        LOG.debug("Initializing MessengerSetupClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newSetupClientBuilder(pageAccessToken).build();
    }

    @Bean
    public UserProfileClient userProfileClient(@Value(PAGE_ACCESS_TOKEN) String pageAccessToken) {
        LOG.debug("Initializing UserProfileClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newUserProfileClientBuilder(pageAccessToken).build();
    }

    @Autowired
    public void setTextMessageEventHandler(TextMessageEventHandler textMessageEventHandler) {
        this.textMessageEventHandler = textMessageEventHandler;
    }
}