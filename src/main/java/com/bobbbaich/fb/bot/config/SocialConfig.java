package com.bobbbaich.fb.bot.config;

import com.bobbbaich.fb.bot.social.mongo.MongoUsersConnectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@EnableSocial
@EnableWebSecurity
public class SocialConfig extends SocialConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SocialConfig.class);

    private static final String LOGIN_FORM_URL = "/signin";

    private MongoUsersConnectionRepository mongoUsersConnectionRepository;

    @Override
    public UserIdSource getUserIdSource() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
            }
            LOG.debug("UserIdSource with value '{}' was received.", authentication.getName());
            return authentication.getName();
        };
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return mongoUsersConnectionRepository;
    }

    @Bean
    public SocialAuthenticationProvider socialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository,
                                                                     SocialUserDetailsService userDetailsService) {
        LOG.debug("SocialAuthenticationProvider was initialised.");
        return new SocialAuthenticationProvider(usersConnectionRepository, userDetailsService);
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint socialAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint(LOGIN_FORM_URL);
    }

    @Bean
    public SocialAuthenticationFilter socialAuthenticationFilter(UsersConnectionRepository connectionRepository,
                                                                 ConnectionFactoryLocator connectionFactoryLocator,
                                                                 AuthenticationManager authenticationManager) {
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(authenticationManager, getUserIdSource(),
                connectionRepository, (SocialAuthenticationServiceLocator) connectionFactoryLocator);
        filter.setFilterProcessesUrl(LOGIN_FORM_URL);
        filter.setSignupUrl("/signup");
        filter.setConnectionAddedRedirectUrl("/test");
        filter.setPostLoginUrl("/test");
        return filter;
    }

    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }

    @Autowired
    public void setMongoUsersConnectionRepository(MongoUsersConnectionRepository mongoUsersConnectionRepository) {
        this.mongoUsersConnectionRepository = mongoUsersConnectionRepository;
    }
}
