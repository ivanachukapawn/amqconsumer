package com.jms.adapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

@Component
public class ConsumerAdapter
{
	private static Logger logger = LogManager.getLogger(ConsumerAdapter.class.getName());

	public void sendToMongo(String json)
	{
		logger.info("sending to MongoDB");
		
		MongoClient	client	=	new	MongoClient();
		
		MongoDatabase database = client.getDatabase("taxpayerdb");

		MongoCollection<Document> collection = database.getCollection("contactInfoCollection");

		logger.info("Convert JSON to BSON document");

		Document document = Document.parse(json);

		collection.insertOne(document);

		client.close();
		
		/*   this is what was prescribed in the course.
		
		MongoClient	client	=	new	MongoClient();
		
		DB db	=	client.getDB("taxpayer");
		
		DBCollection	collection	=	db.getCollection("contactInfo");
		
		logger.info("db=taxpayer, collection=contactInfo : converting JSON to DBObject");
		
		DBObject	object	=	(DBObject)JSON.parse(json);
		
		collection.insert(object);
		
		logger.info("inserted to MongoDb");
		
		*/
	}

}
