package model;

import rewards.Rewardable;

import java.util.ArrayList;
import java.util.List;

public class User implements Rewardable {
    private int id;
    private String mail;
    private String name;
    private final List<String> notifications = new ArrayList<>();
    private final List<Reward> rewards = new ArrayList<>();

    public User (int id,String mail, String name){
        this.id = id;
        this.mail = mail;
        this.name = name;
    }

    public User(String mail, String name) {
        this.mail = mail;
        this.name = name;
    }

    @Override
    public void addReward(Reward reward) {
        rewards.add(reward);
    }

    @Override
    public List<Reward> getRewards() {
        return new ArrayList<>(rewards);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

}
