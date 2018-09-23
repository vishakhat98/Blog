package com.blog.posts.serviceManager;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.blog.posts.database.DatabaseManager;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class PostsService {

	public List<Document> getPosts(){
		DatabaseManager databaseManager = new DatabaseManager();
		MongoCollection<Document> collection = (MongoCollection<Document>) databaseManager.getCollection("blog", "posts");
		List<Document> allPostsData = collection.find().into(new ArrayList<Document>());
		return allPostsData;	
	}
	
	public Document createPost(String request){
		DatabaseManager databaseManager = new DatabaseManager();
		MongoCollection<Document> collection = (MongoCollection<Document>) databaseManager.getCollection("blog", "posts");
		Document inserDoc = Document.parse(request);
		collection.insertOne(inserDoc);
		return inserDoc;
		
	}
	
	public void deletePost(String post_id){
		DatabaseManager databaseManager = new DatabaseManager();
		MongoCollection<Document> collection = (MongoCollection<Document>) databaseManager.getCollection("blog", "posts");
		JSONObject json = new JSONObject(post_id);
		String id = (String) json.get("_id");
		Bson filter = new Document("_id",id);
		collection.deleteOne(filter);
	}
}
