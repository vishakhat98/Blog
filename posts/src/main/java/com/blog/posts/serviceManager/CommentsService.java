package com.blog.posts.serviceManager;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.blog.posts.database.DatabaseManager;
import com.mongodb.client.MongoCollection;

public class CommentsService {

	public void addComment(String request){
		
		DatabaseManager databaseManager = new DatabaseManager();
		MongoCollection<Document> collection = databaseManager.getCollection("blog", "posts");
		JSONObject json = new JSONObject(request);
		String id = json.getString("_id");
		String commentBody = json.getString("comment");
		Bson filter = new Document("_id",id);
		Document comment = new Document("comment", commentBody);
		Document inserComment = new Document().append("$push", new Document("comments",comment));
		collection.updateOne(filter, inserComment);
	}
}
