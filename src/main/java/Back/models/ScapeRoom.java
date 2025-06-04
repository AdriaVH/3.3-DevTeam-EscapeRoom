package Back.models;

import java.util.ArrayList;
import java.util.List;

public class ScapeRoom {
    private int id;
    private String name;
    //private double priceTicket;
    private List<Room> rooms;

    {
        rooms = new ArrayList<>();
    }

    public ScapeRoom(int id, String name) {
        this.id = id;
        this.name = name;
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


    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        //validator();
        this.rooms.add(room);
    }
}
