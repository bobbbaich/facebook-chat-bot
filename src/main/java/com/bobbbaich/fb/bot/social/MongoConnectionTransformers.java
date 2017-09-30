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

    public Function<MongoConnection, String> toUserId() {
        return input -> {
            if (input == null) {
                return null;
            }
            return input.getUserId();
        };
    }

    public Function<MongoConnection, Connection<?>> toConnection() {
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

    public Function<Connection<?>, MongoConnection> fromConnection(final String userId) {
        return input -> {
            if (input == null) {
                return null;
            }
            final ConnectionData cd = input.createData();
            final MongoConnection mongoConnection = new MongoConnection();
            mongoConnection.setCreated(new Date());
            mongoConnection.setUserId(userId);
            mongoConnection.setProviderId(cd.getProviderId());
            mongoConnection.setProviderUserId(cd.getProviderUserId());
            mongoConnection.setDisplayName(cd.getDisplayName());
            mongoConnection.setProfileUrl(cd.getProfileUrl());
            mongoConnection.setImageUrl(cd.getImageUrl());
            mongoConnection.setAccessToken(encrypt(cd.getAccessToken()));
            mongoConnection.setSecret(encrypt(cd.getSecret()));
            mongoConnection.setRefreshToken(encrypt(cd.getRefreshToken()));
            mongoConnection.setExpireTime(cd.getExpireTime());
            return mongoConnection;
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
