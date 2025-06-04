package Back.models;

import front.Menus.reusableMenuTest.MenuOption;

import java.util.List;

public abstract class Item {
    private int id;
    private int roomId;
    private String name;
    private String description;

    protected Item() {}

    protected Item(int roomId, String name, String description) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id; }
    public int getRoomId() {
        return roomId; }
    public String getName() {
        return name; }
    public String getDescription() {
        return description; }

}