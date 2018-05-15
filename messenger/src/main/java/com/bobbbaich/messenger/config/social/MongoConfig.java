package com.bobbbaich.messenger.config.social;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bobbbaich.messenger.repository")
public class MongoConfig {
}
