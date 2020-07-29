package com.katariasoft.technologies.springdatamongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.katariasoft.technologies.springdatamongo.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://127.0.0.1:27017");
	}

	@Override
	protected String getDatabaseName() {
		return "rnr";
	}
	
	

}
