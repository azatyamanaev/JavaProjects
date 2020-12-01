package ru.itis.mongoexamples.driver;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDriverExample {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("uni_db");
        MongoCollection<Document> collection = database.getCollection("students");

        collection.find().forEach(System.out::println);

        Document student1 = new Document();
        student1.append("firstName", "Azat").append("lastName", "Yamanaev").append("address", "ул. Пушкина д. 32 к. 195").append("height", 173);
        collection.insertOne(student1);

        collection.find(new Document("firstName", "Azat")).forEach(System.out::println);

        collection.findOneAndUpdate(new Document().append("height", 173), new Document().append("$set", new Document("weight", 66)));

        collection.find(new Document("firstName", "Azat")).forEach(System.out::println);

        collection.findOneAndDelete(new Document().append("firstName", "Azat"));

        collection.find().forEach(System.out::println);


    }
}
