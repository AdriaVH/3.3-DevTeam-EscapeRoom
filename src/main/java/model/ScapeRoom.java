package model;

import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;

import java.math.BigDecimal;

public class ScapeRoom {
    private int id;
    private String name;
    private BigDecimal ticket_Price;

    public ScapeRoom(int id, String name,  BigDecimal ticketPrice) {
        this.id = id;
        this.name = name;
        this.ticket_Price = ticketPrice;

    }

    public ScapeRoom(String name , BigDecimal ticketPrice) {
        this.name = name;
        this.ticket_Price = ticketPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTicketPrice() {
        return ticket_Price;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticket_Price = ticketPrice;
    }

    public double getTotalPrice () {
        ScapeRoomDAO dao = new ScapeRoomDAOSQL();
        return dao.totalPrice(id);
    }
    @Override
    public String toString() {
        return "ScapeRoom: " + name + "\n" +
                "ID: " + id + "\n" +
                "Ticket price: " + ticket_Price + "\n";
    }
}