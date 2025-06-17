package model;

public class Reward {
    private int id;
    private int playerId;
    private String description;

    public Reward(int id, int player_Id, String description) {
        this.description = description;
        this.id = id;
        this.playerId = player_Id;
    }
    public Reward(int player_Id, String description) {
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
        return "Reward: " + "\n" +
                "Description: " + description + "\n" +
                "Id: " + id + "\n" +
                "Player_Id: " + playerId + "\n";
    }
}