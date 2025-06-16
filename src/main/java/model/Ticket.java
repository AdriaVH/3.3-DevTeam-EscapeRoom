package model;

import java.math.BigDecimal;

public class Ticket {
    private int id;
    private ScapeRoom scapeRoom;
    private Player player;

    public Ticket(int id, ScapeRoom scapeRoom, Player player) {
        this.id = id;
        this.scapeRoom = scapeRoom;
        this.player = player;
    }

    public Ticket(ScapeRoom scapeRoom, Player player) {
        this.scapeRoom = scapeRoom;
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public String getScapeRoom() {
        return scapeRoom.getName();
    }

    public String getPlayer() {
        return player.getName();
    }

    public BigDecimal getTicketPrice(){
        return scapeRoom.getTicketPrice();
    }

    public String getPlayerMail() {
        return player.getMail();
    }

    public void setPlayerMail(String playerMail){
        this.player.setMail(playerMail);
    }

    public int getScapeRoomId(){
        return scapeRoom.getId();
    }
}