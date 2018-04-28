package com.bobbbaich.fb.bot.messenger.config;

import com.github.messenger4j.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MessengerConfig {
    private final MessengerProperty props;

    /**
     * Initializes the {@code Messenger}.
     */
    @Bean
    public Messenger messengerSendClient() {
        return Messenger.create(props.getPageAccessToken(), props.getAppSecret(), props.getVerifyToken());
    }
}