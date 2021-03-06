package com;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoClient;

@SpringBootApplication(scanBasePackages = { "com" })
public class MongoRunner {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		//SpringApplication.run(MongoRunner.class, args);

		MongoClient mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("EasyTapp");
		DBCollection dbCollections = db.getCollection("User");
		
		DBCollection table = db.getCollection("User");
		BasicDBObject document = new BasicDBObject();
		document.put("id", "1");
		document.put("name","Nikhil");
		document.put("salary","1000");
		table.insert(document);
		
		List<String> dbs = mongo.getDatabaseNames();
		for (String db1 : dbs) {
			System.out.println(db1);
		}
	}

}
