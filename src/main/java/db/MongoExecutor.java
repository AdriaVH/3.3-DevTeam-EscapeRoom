package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class MongoExecutor {

    private static MongoExecutor instance;
    private final MongoClient client;
    private final MongoDatabase database;

    private MongoExecutor() {
        try {
            // Ensure TLS 1.2 is used for secure connection
            System.setProperty("jdk.tls.client.protocols", "TLSv1.2");

            // Load environment variables
            Dotenv dotenv = Dotenv.configure().filename(".env.mongo").load();
            String uri = dotenv.get("MONGO_URI");

            if (uri == null || uri.isEmpty()) {
                throw new RuntimeException("MONGO_URI not found in .env.mongo");
            }

            this.client = MongoClients.create(uri);
            this.database = client.getDatabase("scaperoom");

            // Test connection
            Document ping = new Document("ping", 1);
            database.runCommand(ping);
            System.out.println("✅ Successfully connected to MongoDB.");

        } catch (Exception e) {
            System.err.println("❌ MongoDB connection failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("MongoExecutor initialization failed", e);
        }
    }

    public static MongoExecutor getInstance() {
        if (instance == null) {
            instance = new MongoExecutor();
        }
        return instance;
    }

    public MongoCollection<Document> getCollection(String name) {
        return database.getCollection(name);
    }

    public MongoClient getClient() {
        return client;
    }
}
