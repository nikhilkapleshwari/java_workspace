package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@SpringBootApplication(scanBasePackages={ "com" })
public class MongoApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "EasyTapp"));
		
		//Person p = new Person("Joe", 34);

	    // Insert is used to initially store the object into the database.
	   // mongoOps.insert(p);
	    //System.out.println("Inserted successfully.");
	    
	    SpringApplication.run(MongoApp.class, args);
	}

}
