package model;

import java.math.BigDecimal;

public class ScapeRoom {
    private int id;
    private String name;
    private BigDecimal ticketPrice;

    //Nou constructor amb el price (BigDecimal)

    public ScapeRoom(int id, String name,  BigDecimal ticketPrice) {
        this.id = id;
        this.name = name;
        this.ticketPrice = ticketPrice;

    }
    public ScapeRoom(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public ScapeRoom(String name) {
        this.name = name;
    }

    public BigDecimal getTicketPrice() { return getTicketPrice();
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
}