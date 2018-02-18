package com.bobbbaich.fb.bot.config;

import com.bobbbaich.fb.bot.social.mongo.ConnectionConverter;
import com.bobbbaich.fb.bot.social.mongo.ConnectionService;
import com.bobbbaich.fb.bot.social.mongo.MongoConnectionService;
import com.bobbbaich.fb.bot.social.mongo.MongoUsersConnectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    private final MongoTemplate mongoTemplate;
    private final ConnectionSignUp connectionSignUp;

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        ConnectionService mongoService = getConnectionService(connectionFactoryLocator);
        MongoUsersConnectionRepository mongoUsersConnectionRepository = new MongoUsersConnectionRepository(mongoService, connectionFactoryLocator, getTextEncryptor());
        mongoUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        return mongoUsersConnectionRepository;
    }

    @Bean
    public TextEncryptor getTextEncryptor() {
        return Encryptors.noOpText();
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository connectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    private ConnectionService getConnectionService(ConnectionFactoryLocator connectionFactoryLocator) {
        return new MongoConnectionService(mongoTemplate, new ConnectionConverter(connectionFactoryLocator, getTextEncryptor()));
    }
}