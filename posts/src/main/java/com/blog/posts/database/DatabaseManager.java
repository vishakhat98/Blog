package com.blog.posts.database;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {

	MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
	MongoClient client = new MongoClient(new ServerAddress(), options);
	
	public MongoDatabase getDatabase(String db){
		MongoDatabase db1 = client.getDatabase(db);
		return db1;
	}
	
	public MongoCollection<Document> getCollection(String db, String coll){
		MongoDatabase db1 = client.getDatabase(db);
		MongoCollection<Document> collection = db1.getCollection(coll);
		return collection;
	}
}
