package model;

import enums.DifficultLevel;
import enums.Theme;

public class Room {
    private int id;
    private int scapeRoomId;
    private DifficultLevel difficultLevel;
    private Theme theme;



    public Room(int scapeRoomId, DifficultLevel difficultLevel, Theme theme) {
        this.scapeRoomId = scapeRoomId;
        this.difficultLevel = difficultLevel;
        this.theme = theme;
    }

    public Room(int id, int scapeRoomId, DifficultLevel difficultLevel, Theme theme) {
        this.id = id;
        this.scapeRoomId = scapeRoomId;
        this.difficultLevel = difficultLevel;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScapeRoomId() {
        return scapeRoomId;
    }

    public void setScapeRoomId(int scapeRoomId) {
        this.scapeRoomId = scapeRoomId;
    }

    public DifficultLevel getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(DifficultLevel difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}