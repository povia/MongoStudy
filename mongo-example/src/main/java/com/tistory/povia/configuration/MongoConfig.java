package com.tistory.povia.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String uri;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public @Bean MongoClient mongoClient() {
		return MongoClients.create(uri);
	}


}
