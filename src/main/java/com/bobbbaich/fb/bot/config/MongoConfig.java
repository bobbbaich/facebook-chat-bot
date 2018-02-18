package com.bobbbaich.fb.bot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bobbbaich.fb.bot.repository")
public class MongoConfig {
}
