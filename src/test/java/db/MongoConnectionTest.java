package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoConnectionTest {

    @Test
    void testMongoConnection() {
        try {
            MongoExecutor executor = MongoExecutor.getInstance();
            MongoClient client = executor.getClient();

            assertNotNull(client, "MongoClient should not be null");

            MongoDatabase db = client.getDatabase("scaperoom");
            assertNotNull(db, "Database should not be null");

            MongoCollection<Document> collection = db.getCollection("Players");
            assertNotNull(collection, "Collection 'Players' should not be null");

            System.out.println("✅ MongoDB test passed. Collection exists.");

        } catch (Exception e) {
            fail("❌ MongoDB connection failed: " + e.getMessage());
        }
    }
}
