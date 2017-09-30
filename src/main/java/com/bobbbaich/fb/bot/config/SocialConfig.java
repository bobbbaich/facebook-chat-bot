package com.bobbbaich.fb.bot.config;

import com.bobbbaich.fb.bot.social.MongoUsersConnectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.*;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialUserDetailsService;

@Configuration
@EnableSocial
@EnableWebSecurity
public class SocialConfig extends SocialConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SocialConfig.class);

    private static final String FACEBOOK_CLIENT_ID = "facebook.client.clientId";
    private static final String FACEBOOK_CLIENT_SECRET = "facebook.client.clientSecret";

    private MongoOperations mongo;
    private AuthenticationManager authenticationManager;
    private MongoUsersConnectionRepository mongoUsersConnectionRepository;

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        LOG.debug("Facebook connection was initialized: {}", connection);
        return connection != null ? connection.getApi() : null;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty(FACEBOOK_CLIENT_ID),
                env.getProperty(FACEBOOK_CLIENT_SECRET)
        ));
    }

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
        LOG.debug("MongoUsersConnectionRepository was initialised.");
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
        return new LoginUrlAuthenticationEntryPoint("/signin");
    }

    @Bean
    @Qualifier()
    public SocialAuthenticationFilter socialAuthenticationFilter(UsersConnectionRepository connectionRepository,
                                                                 ConnectionFactoryLocator connectionFactoryLocator) {
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(authenticationManager, getUserIdSource(),
                connectionRepository, (SocialAuthenticationServiceLocator) connectionFactoryLocator);
        filter.setFilterProcessesUrl("/signin");
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
    public void setMongo(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}