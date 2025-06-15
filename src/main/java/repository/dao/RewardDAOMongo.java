package repository.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import db.MongoExecutor;
import model.Reward;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class RewardDAOMongo {

    private final MongoCollection<Document> collection = MongoExecutor.getInstance().getCollection("Reward");

    public void insert(Reward reward) {
        Document doc = new Document("playerMail", reward.getPlayerMail())
                .append("type", reward.getType())
                .append("description", reward.getDescription());
        collection.insertOne(doc);
    }

    public List<Reward> findByPlayerMail(String mail) {
        List<Reward> rewards = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("playerMail", mail))) {
            rewards.add(new Reward(
                    doc.getString("playerMail"),
                    doc.getString("type"),
                    doc.getString("description")));
        }
        return rewards;
    }
}
