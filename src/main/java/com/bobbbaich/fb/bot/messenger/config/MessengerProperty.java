package com.bobbbaich.fb.bot.messenger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "messenger")
public class MessengerProperty {
    private static final String URL_REGEXP = "(https?|ftp)://(-\\.)?([^\\s/?\\.#-]+\\.?)+(/[^\\s]*)?$";

    @NotNull
    private String verifyToken;
    @NotNull
    private String pageAccessToken;
    @NotNull
    private String appSecret;
    @NotNull
    private String greeting;
    @NotNull
    private String botGeneralInfo;
    @NotNull
    private String factRequest;

    @Pattern(regexp = URL_REGEXP)
    private List<String> whitelistedURLs;
    @Pattern(regexp = URL_REGEXP)
    private String homeURL;

    private boolean inTest;
}