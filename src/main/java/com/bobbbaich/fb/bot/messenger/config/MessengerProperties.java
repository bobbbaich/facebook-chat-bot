package com.bobbbaich.fb.bot.messenger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "messenger")
public class MessengerProperties {
    private String verifyToken;
    private String pageAccessToken;
    private String appSecret;

    private String getStartedPayload;
    private String greeting;
    private String helpPayload;
}