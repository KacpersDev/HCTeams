package dev.kacperm.shared.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

@Getter
public class MongoManager {

    private final MongoCollection<Document> profiles, factions;

    public MongoManager(String uri, String database) {
        MongoClient mongoClient = MongoClients.create(new ConnectionString(uri));
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);

        this.profiles = mongoDatabase.getCollection("profiles");
        this.factions = mongoDatabase.getCollection("factions");
    }
}
