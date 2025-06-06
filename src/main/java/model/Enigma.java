package model;

import enums.Theme;

public class Enigma extends Item {
    private Integer roomId;
    private Theme theme;


    public Enigma(Integer roomId, String name, String description, Theme theme) {
        super(name, description);
        this.roomId = roomId;
        this.theme = theme;
    }

    public Enigma( int id,Integer roomId, String name,  Theme theme, String description) {
        super(id,name, description);
        this.roomId = roomId;
        this.theme = theme;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}