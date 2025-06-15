package model;

public class Reward {
    private String playerMail;
    private String type;
    private String description;

    public Reward(String playerMail, String type, String description) {
        this.playerMail = playerMail;
        this.type = type;
        this.description = description;
    }

    public String getPlayerMail() {
        return playerMail;
    }

    public void setPlayerMail(String playerMail) {
        this.playerMail = playerMail;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "playerMail='" + playerMail + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
