package repository.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import db.MongoExecutor;
import model.Player;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class PlayerDAOMongo implements PlayerDAO {

    private final MongoCollection<Document> collection = MongoExecutor.getInstance().getCollection("Players");

    @Override
    public void insert(Player player) {
        Document doc = new Document("mail", player.getMail())
                .append("name", player.getName());
        collection.insertOne(doc);
    }

    @Override
    public void update(Player player) {
        Bson filter = Filters.eq("mail", player.getMail());
        Document update = new Document("$set", new Document("name", player.getName()));
        collection.updateOne(filter, update);
    }

    @Override
    public void delete(int id) {
        System.out.println("⚠️ Not supported: MongoDB does not use integer IDs. Use deleteByMail() instead.");
    }

    public void deleteByMail(String mail) {
        collection.deleteOne(Filters.eq("mail", mail));
    }

    @Override
    public Player findById(int id) {
        System.out.println("⚠️ Not supported: MongoDB does not use integer IDs. Use findByMail() instead.");
        return null;
    }

    public Player findByMail(String mail) {
        Document doc = collection.find(Filters.eq("mail", mail)).first();
        if (doc != null) {
            return new Player(doc.getString("mail"), doc.getString("name"));
        }
        return null;
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        for (Document doc : collection.find()) {
            players.add(new Player(doc.getString("mail"), doc.getString("name")));
        }
        return players;
    }
}
