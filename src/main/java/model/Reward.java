package model;

public class Reward {
    private Player player;
    private String description;

    public Reward(Player player, String description) {
        this.player = player;
        this.description = description;
    }

    public Player getPlayer() { return this.player;}
    public String getPlayerMail() {
        return player.getMail();
    }

    public void setPlayerMail(String playerMail) {
        this.player.setMail(playerMail);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Reward: " + "\n" +
                "PlayerMail: " + player.getMail() + "\n" +
                "Description: " + description;
    }
}
