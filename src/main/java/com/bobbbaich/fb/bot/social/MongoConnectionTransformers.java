package com.bobbbaich.fb.bot.social;

import com.google.common.base.Function;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MongoConnectionTransformers {
    private final ConnectionFactoryLocator connectionFactoryLocator;
    private final TextEncryptor textEncryptor;

    public MongoConnectionTransformers(ConnectionFactoryLocator connectionFactoryLocator, TextEncryptor textEncryptor) {
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.textEncryptor = textEncryptor;
    }

    public Function<SocialConnection, String> toUserId() {
        return input -> {
            if (input == null) {
                return null;
            }
            return input.getUserId();
        };
    }

    public Function<SocialConnection, Connection<?>> toConnection() {
        return input -> {
            if (input == null) {
                return null;
            }
            final ConnectionData cd = new ConnectionData(
                    input.getProviderId(),
                    input.getProviderUserId(),
                    input.getDisplayName(),
                    input.getProfileUrl(),
                    input.getImageUrl(),
                    input.getAccessToken(),
                    input.getSecret(),
                    input.getRefreshToken(),
                    input.getExpireTime()
            );
            final ConnectionFactory<?> connectionFactory = connectionFactoryLocator
                    .getConnectionFactory(input.getProviderId());
            return connectionFactory.createConnection(cd);
        };
    }

    public Function<Connection<?>, SocialConnection> fromConnection(final String userId) {
        return input -> {
            if (input == null) {
                return null;
            }
            final ConnectionData cd = input.createData();
            final SocialConnection socialConnection = new SocialConnection();
            socialConnection.setCreated(new Date());
            socialConnection.setUserId(userId);
            socialConnection.setProviderId(cd.getProviderId());
            socialConnection.setProviderUserId(cd.getProviderUserId());
            socialConnection.setDisplayName(cd.getDisplayName());
            socialConnection.setProfileUrl(cd.getProfileUrl());
            socialConnection.setImageUrl(cd.getImageUrl());
            socialConnection.setAccessToken(encrypt(cd.getAccessToken()));
            socialConnection.setSecret(encrypt(cd.getSecret()));
            socialConnection.setRefreshToken(encrypt(cd.getRefreshToken()));
            socialConnection.setExpireTime(cd.getExpireTime());
            return socialConnection;
        };
    }

    private String encrypt(final String decrypted) {
        if (decrypted == null) {
            return null;
        }
        return textEncryptor.encrypt(decrypted);
    }

    private String decrypt(final String encrypted) {
        if (encrypted == null) {
            return null;
        }
        return textEncryptor.decrypt(encrypted);
    }
}
