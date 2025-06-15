package repository.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import db.MongoExecutor;
import model.Ticket;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOMongo {

    private final MongoCollection<Document> collection = MongoExecutor.getInstance().getCollection("Tickets");

    public void insert(Ticket ticket) {
        Document doc = new Document("playerMail", ticket.getPlayerMail())
                .append("roomId", ticket.getScapeRoomId())
                .append("date", ticket.getDate().toString());
        collection.insertOne(doc);
    }

    public List<Ticket> findByPlayerMail(String mail) {
        List<Ticket> tickets = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("playerMail", mail))) {
            tickets.add(new Ticket(
                    doc.getString("playerMail"),
                    doc.getInteger("roomId"),
                    LocalDate.parse(doc.getString("date"))
            ));
        }
        return tickets;
    }
}
