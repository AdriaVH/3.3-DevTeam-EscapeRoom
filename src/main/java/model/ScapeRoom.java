package model;

public class ScapeRoom {
    private int id;
    private String name;

    public ScapeRoom() {
    }

    public ScapeRoom(String name) {
        this.name = name;
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