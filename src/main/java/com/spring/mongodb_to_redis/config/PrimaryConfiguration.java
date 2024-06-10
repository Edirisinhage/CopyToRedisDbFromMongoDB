package com.spring.mongodb_to_redis.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.spring.mongodb_to_redis.primary.repository",
        mongoTemplateRef = "primaryMongoTemplate"

)
public class PrimaryConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.primary.url}")
    private String url;

    @Value("${spring.data.mongodb.primary.database}")
    private String databaseName;


    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean(name="primaryMongoClient")
    public MongoClient primaryMongoClient(){
        return MongoClients.create(url);

    }


    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate(@Qualifier("primaryMongoClient") MongoClient mongoClient) {
        System.out.println("Connection Success");
        return new MongoTemplate(mongoClient, getDatabaseName());
    }



}
