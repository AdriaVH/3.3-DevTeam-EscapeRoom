package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String mail;
    private String name;
    private final List<String> notifications = new ArrayList<>();
    private final List<Reward> rewards = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    public Player(int id, String mail, String name) {
        this.id = id;
        this.mail = mail;
        this.name = name;
    }

    public Player(String mail, String name) {
        this.mail = mail;
        this.name = name;
    }

    public void addNotification(String message) {
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player: " + name + "\n" +
                "Id: " + id + "\n" +
                "Mail: " + mail + "\n" +
                "Notifications: " + notifications + "\n" +
                "Rewards: " + rewards + "\n" +
                "Ticket: " + tickets;
    }
}
