package model;

import java.math.BigDecimal;

public class ScapeRoom {
    private int id;
    private String name;
    private BigDecimal ticketPrice;

    public ScapeRoom(int id, String name,  BigDecimal ticketPrice) {
        this.id = id;
        this.name = name;
        this.ticketPrice = ticketPrice;

    }

    public ScapeRoom(String name , BigDecimal ticketPrice) {
        this.name = name;
        this.ticketPrice = ticketPrice;
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
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "ScapeRoom: " + name + "\n" +
                "ID: " + id + "\n" +
                "Ticket price: " + ticketPrice + "\n";
    }
}