/*package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

//@EnableMongoRepositories(basePackageClasses=UserRepository.class)
@Configuration
public class MongoDbConfig extends AbstractMongoConfiguration{

	@Override
	@Bean
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient("127.0.0.1");
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "EasyTapp";
	}

}
*/