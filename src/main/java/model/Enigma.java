package model;

import enums.Theme;

public class Enigma extends Item {
    private int roomId;
    private Theme theme;

    public Enigma() {
        super();
    }
    public Enigma(int roomId, String name, String description, Theme theme) {
        super(name, description);
        this.roomId = roomId;
        this.theme = theme;
    }

    public Enigma(int roomId, String name, String description, int id, Theme theme) {
        super(id,name, description);
        this.roomId = roomId;
        this.theme = theme;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}