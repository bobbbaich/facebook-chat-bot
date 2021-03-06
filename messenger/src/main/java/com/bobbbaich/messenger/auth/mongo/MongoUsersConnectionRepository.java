package com.bobbbaich.messenger.auth.mongo;


import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * {@link UsersConnectionRepository} that uses the JDBC API to persist connection data to a relational database.
 * The supporting schema is defined in JdbcMultiUserConnectionRepository.sql.
 */
public class MongoUsersConnectionRepository implements UsersConnectionRepository {

    private final ConnectionService mongoService;

    private final ConnectionFactoryLocator connectionFactoryLocator;

    private final TextEncryptor textEncryptor;

    private ConnectionSignUp connectionSignUp;

    public MongoUsersConnectionRepository(ConnectionService mongoService,
                                          ConnectionFactoryLocator connectionFactoryLocator,
                                          TextEncryptor textEncryptor) {
        this.mongoService = mongoService;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.textEncryptor = textEncryptor;
    }

    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        List<String> localUserIds = mongoService.getUserIds(key.getProviderId(), key.getProviderUserId());
        if (localUserIds.size() == 0 && connectionSignUp != null) {
            String newUserId = connectionSignUp.execute(connection);
            if (newUserId != null) {
                createConnectionRepository(newUserId).addConnection(connection);
                return Collections.singletonList(newUserId);
            }
        }
        return localUserIds;
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {

        return mongoService.getUserIds(providerId, providerUserIds);
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        return new MongoConnectionRepository(userId, mongoService, connectionFactoryLocator);
    }

}