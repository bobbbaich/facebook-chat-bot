package com.bobbbaich.fb.bot.messenger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Pattern;
import java.util.List;


@Data
@Configuration
@ConfigurationProperties(prefix = "messenger")
public class MessengerProperties {
    private static final String URL_REGEXP = "(https?|ftp)://(-\\.)?([^\\s/?\\.#-]+\\.?)+(/[^\\s]*)?$";

    private String verifyToken;
    private String pageAccessToken;
    private String appSecret;

    private String greeting;

    @Pattern(regexp = URL_REGEXP)
    private List<String> whitelistedURLs;
    @Pattern(regexp = URL_REGEXP)
    private String homeURL;

    private boolean inTest;

    private String getStartedPayload;
    private String helpPayload;
    private String startAnalysisPayload;
}