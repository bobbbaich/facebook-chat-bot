package com.bobbbaich.fb.bot.social;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.transform;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Primary
@Repository
public class MongoUsersConnectionRepository implements UsersConnectionRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MongoUsersConnectionRepository.class);

    private final MongoOperations mongo;
    private final MongoConnectionTransformers mongoConnectionTransformers;
    private final ConnectionSignUp connectionSignUp;
    private final MongoConnectionRepository mongoConnectionRepository;


    public MongoUsersConnectionRepository(MongoOperations mongo, MongoConnectionTransformers mongoConnectionTransformers,
                                          ConnectionSignUp connectionSignUp, MongoConnectionRepository mongoConnectionRepository) {
        this.mongo = mongo;
        this.mongoConnectionTransformers = mongoConnectionTransformers;
        this.connectionSignUp = connectionSignUp;
        this.mongoConnectionRepository = mongoConnectionRepository;
    }

    @Override
    public List<String> findUserIdsWithConnection(final Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        Query query = query(where("providerId").is(key.getProviderId()).and("providerUserId").is(key.getProviderUserId()));
        query.fields().include("userId");
        List<String> localUserIds = ImmutableList.copyOf(transform(mongo.find(query, MongoConnection.class), mongoConnectionTransformers.toUserId()));
        if (localUserIds.isEmpty() && connectionSignUp != null) {
            String newUserId = connectionSignUp.execute(connection);
            if (newUserId != null) {
                createConnectionRepository(newUserId).addConnection(connection);
                return ImmutableList.of(newUserId);
            }
        }
        return localUserIds;
    }

    @Override
    public Set<String> findUserIdsConnectedTo(final String providerId, final Set<String> providerUserIds) {
        Query query = query(where("providerId").is(providerId).and("providerUserId").in(providerUserIds));
        query.fields().include("userId");
        return ImmutableSet.copyOf(transform(mongo.find(query, MongoConnection.class), mongoConnectionTransformers.toUserId()));
    }

    @Override
    public ConnectionRepository createConnectionRepository(final String userId) {
        checkArgument(userId != null, "userId must be defined");
        mongoConnectionRepository.setUserId(userId);
        return mongoConnectionRepository;
    }
}