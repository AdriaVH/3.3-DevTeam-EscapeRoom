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
            System.setProperty("jdk.tls.client.protocols", "TLSv1.2");

            Dotenv dotenv = Dotenv.configure()
                    .filename(".env")
                    .load();

            String uri = dotenv.get("MONGO_URI");
            String dbName = dotenv.get("MONGO_DATABASE");

            if (uri == null || dbName == null || uri.isEmpty() || dbName.isEmpty()) {
                throw new RuntimeException("❌ Missing MONGO_URI or MONGO_DATABASE in .env.mongo");
            }

            this.client = MongoClients.create(uri);
            this.database = client.getDatabase(dbName);

            // Optional ping test
            Document ping = new Document("ping", 1);
            database.runCommand(ping);
            System.out.println("✅ Connected to MongoDB database: " + dbName);

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
