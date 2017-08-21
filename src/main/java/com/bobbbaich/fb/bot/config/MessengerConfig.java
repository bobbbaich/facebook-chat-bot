package com.bobbbaich.fb.bot.config;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

import static com.bobbbaich.fb.bot.config.Profile.REMOTE;

@Configuration
public class MessengerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MessengerConfig.class);

    private static final String PAGE_ACCESS_TOKEN = "messenger4j.pageAccessToken";
    private static final String APP_SECRET = "messenger4j.appSecret";
    private static final String VERIFY_TOKEN = "messenger4j.verifyToken";
    private static final String LOCAL_APP_SECRET = "messenger4j.local.appSecret";
    private static final String LOCAL_VERIFY_TOKEN = "messenger4j.local.verifyToken";
    private static final String LOCAL_PAGE_ACCESS_TOKEN = "messenger4j.local.pageAccessToken";

    @Resource
    private Environment env;
    private TextMessageEventHandler textMessageEventHandler;


    /**
     * Initializes the {@code MessengerSendClient}.
     */
    @Bean
    public MessengerSendClient messengerSendClient() {
        String pageAccessToken = LOCAL_PAGE_ACCESS_TOKEN;
        if (env.acceptsProfiles(REMOTE.name())) {
            pageAccessToken = env.getRequiredProperty(PAGE_ACCESS_TOKEN);
        }
        LOG.debug("Initializing MessengerSendClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
    }

    /**
     * Initializes the {@code MessengerReceiveClient}.
     * <p>
     * the {@code Application Secret}
     * the {@code Verification Token} that has been provided by you during the setup of the {@code
     * Webhook}
     */

    @Bean
    public MessengerReceiveClient messengerReceiveClient() {
        String appSecret = env.getProperty(LOCAL_APP_SECRET);
        String verifyToken = env.getProperty(LOCAL_VERIFY_TOKEN);
        if (env.acceptsProfiles(REMOTE.name())) {
            appSecret = env.getRequiredProperty(APP_SECRET);
            verifyToken = env.getRequiredProperty(VERIFY_TOKEN);
        }

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
