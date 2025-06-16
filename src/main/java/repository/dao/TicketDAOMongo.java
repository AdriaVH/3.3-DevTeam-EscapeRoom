package repository.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import db.MongoExecutor;
import model.Player;
import model.ScapeRoom;
import model.Ticket;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOMongo {

    private final MongoCollection<Document> collection = MongoExecutor.getInstance().getCollection("Tickets");

    public void insert(Ticket ticket) {
        Document doc = new Document("playerMail", ticket.getPlayerMail())
                .append("roomId", ticket.getScapeRoomId());
        collection.insertOne(doc);
        //Per inserir fa falta player.getMail & scapeRoom.getName
    }

    public List<Ticket> findByPlayerMail(String mail) {
        List<Ticket> tickets = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("playerMail", mail))) {
            tickets.add(new Ticket(
                    (ScapeRoom) doc.get("roomId"),
                    (Player) doc.get("playerMail")));
        }
        return tickets;
    }
}
