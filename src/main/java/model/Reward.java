package model;

public class Reward {
    private String description;
    private int points;

    public Reward(String description, int points) {
        this.description = description;
        this.points = points;
    }

    public String getDescroption() {
        return description;
    }
    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Reward: " + "\n" +
                "Description: " + description + "\n" +
                "Points: " + points;
    }
}
