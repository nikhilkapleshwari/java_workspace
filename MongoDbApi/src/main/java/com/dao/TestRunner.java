package com.dao;

import java.time.LocalDateTime;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlainMongoDbConnector plainMongoDbConnector=new PlainMongoDbConnector("localhost",27017);
		User user=new User("Nikhil","12345","admin",Boolean.FALSE,"0000",LocalDateTime.now());
		//plainMongoDbConnector.insert(user,"EasyTapp","User");
		plainMongoDbConnector.find(1,"EasyTapp","User");
		//plainMongoDbConnector.update(user,"EasyTapp","User");
		//plainMongoDbConnector.getCounterValue();
	}

}
