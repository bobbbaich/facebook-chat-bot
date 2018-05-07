package com.bobbbaich.social.mongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Mongodb collection for the spring social connections.
 */
@Data
@Document(collection = "connections")
@CompoundIndexes({
        @CompoundIndex(name = "connections_rank_idx", def = "{'userId': 1, 'providerId': 1, 'rank': 1}", unique = true),
        @CompoundIndex(name = "connections_primary_idx", def = "{'userId': 1, 'providerId': 1, 'providerUserId': 1}", unique = true)
})
public class MongoConnection {
    @Id
    private ObjectId id;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String providerId;

    private String providerUserId;

    @Range(min = 1, max = 9999)
    private int rank; //not null
    private String displayName;
    private String profileUrl;
    private String imageUrl;

    @NotEmpty
    private String accessToken;

    private String secret;
    private String refreshToken;
    private Long expireTime;
}