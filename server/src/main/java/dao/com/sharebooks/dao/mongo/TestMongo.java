package com.sharebooks.dao.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestMongo {

	public static void main(String args[]) {

		// Creating a Mongo client
		MongoClient mongo = new MongoClient("localhost", 27017);

		// Creating Credentials
		// MongoCredential credential;
		// credential = MongoCredential.createCredential("sampleUser", "myDb",
		// "password".toCharArray());
		System.out.println("Connected to the database successfully");

		// Accessing the database
		MongoDatabase database = mongo.getDatabase("nitj");

		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("ece");
		System.out.println("Collection ECE selected successfully");

		Document document = new Document("title", "MongoDB").append("id", 1).append("description", "database")
				.append("likes", 100).append("url", "http://www.tutorialspoint.com/mongodb/")
				.append("by", "tutorials point");
		collection.insertOne(document);
		System.out.println("Document inserted successfully");
	}
}
