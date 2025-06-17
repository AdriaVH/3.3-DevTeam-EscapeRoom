package model;

public class Reward {
    private int id;
    private int playerId;
    private String description;

    public Reward(String description, int id, int player_Id) {
        this.description = description;
        this.id = id;
        this.playerId = player_Id;
    }

    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", player_Id=" + playerId +
                '}';
    }
}