package com.dao;

import java.net.UnknownHostException;
import java.util.Objects;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class PlainMongoDbConnector implements DbConnector {

	private static MongoClient mongoClient=null;
	
	public PlainMongoDbConnector(String server,Integer port) {
		
		try {
			if(mongoClient==null)
				mongoClient=new MongoClient(server,port);	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insert(User user,String db,String collection) {
		
		DB database = mongoClient.getDB(db);
		BasicDBObject document = new BasicDBObject();
	
		document.put("userId",getCounterValue());
		document.put("name",user.getName());
		document.put("password",user.getPassword());
		document.put("role",user.getRole());
		document.put("isAccountVerified",user.isAccountVerified());
		document.put("vCode",user.getvCode());
		document.put("vCodeValidTs",user.getvCodeValidTs().toString());
		
		DBCollection dbCollection = database.getCollection(collection);
		dbCollection.insert(document);
	}
	
	public void find(Integer id,String db,String collection) {
		DB dataBase = mongoClient.getDB(db);
		DBCollection dbCollection = dataBase.getCollection(collection);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userId",id);

		DBCursor cursor = dbCollection.find(searchQuery);

		while (cursor.hasNext()) {
			User user=(User)cursor.next();
			System.out.println(user);
		}
		
	}
	public void update(User user,String db,String collection) {
		
		DB dataBase = mongoClient.getDB(db);
		DBCollection dbCollection = dataBase.getCollection(collection);
		
		BasicDBObject query = new BasicDBObject();
		query.put("userId",user.getId());

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("vCode", "7777");
					
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		dbCollection.update(query, updateObj);
		
	}
	
	public int getCounterValue() {
		
		DB db = mongoClient.getDB("EasyTapp");
		DBCollection collection = db.getCollection("counters");
		
		BasicDBObject doc=(BasicDBObject) collection.findOne();
		
		if(Objects.isNull(doc)) {
			BasicDBObject document = new BasicDBObject();
			document.put("seq",Integer.valueOf("1"));
			collection.insert(document);
			return 1;
		}else {
			int seqValue=((Double)doc.get("seq")).intValue();
			System.out.println();
			
			BasicDBObject queryObj=new BasicDBObject();
			queryObj.put("seq",seqValue);
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("seq",++seqValue);
						
			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);
			
			collection.update(queryObj, updateObj);
			return seqValue;
		}
		
		
	}
}
