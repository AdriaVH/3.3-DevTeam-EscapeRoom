package model;

import java.math.BigDecimal;

public class Ticket {
    private int id;
    private int scapeRoomId;
    private int playerId;

    public Ticket(int id, int scapeRoom_Id, int player_Id) {
        this.id = id;
        this.scapeRoomId = scapeRoom_Id;
        this.playerId = player_Id;
    }

    public Ticket(int scapeRoom_Id, int player_Id) {
        this.scapeRoomId = scapeRoom_Id;
        this.playerId = player_Id;
    }


    public int getId() {
        return id;
    }

    public int getScapeRoomId() {
        return scapeRoomId;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "Ticket: " + "\n" +
                "Id: " + id + "\n" +
                "ScapeRoomId: " + scapeRoomId + "\n" +
                "PlayerId: " + playerId;
    }
}