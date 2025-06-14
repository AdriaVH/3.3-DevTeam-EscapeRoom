package model;

public class Reward {
    private String descroption;
    private int points;

    public Reward(String descroption, int points) {
        this.descroption = descroption;
        this.points = points;
    }

    public String getDescroption() {
        return descroption;
    }
    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "descroption='" + descroption + '\'' +
                ", points=" + points +
                '}';
    }
}
