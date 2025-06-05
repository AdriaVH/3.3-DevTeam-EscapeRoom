package model;

import java.util.ArrayList;
import java.util.List;

public class User{
    private String mail;
    private String name;
    private final List<String> notifications = new ArrayList<>();

    public User(String mail, String name) {

        this.mail = mail;
        this.name = name;
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
    public void addNotification(String message) {
        notifications.add(message);
    }

    /** Devuelve todas las notificaciones recibidas hasta el momento */
    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

}
