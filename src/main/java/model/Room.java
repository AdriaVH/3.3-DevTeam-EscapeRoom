package model;

import enums.DifficultLevel;
import enums.Theme;

public class Room {
    private int id;
    private Integer scapeRoomId;
    private DifficultLevel difficultLevel;
    private Theme theme;



    public Room(Integer scapeRoomId, DifficultLevel difficultLevel, Theme theme) {
        this.scapeRoomId = scapeRoomId;
        this.difficultLevel = difficultLevel;
        this.theme = theme;
    }

    public Room(int id, Integer scapeRoomId, DifficultLevel difficultLevel, Theme theme) {
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

    public Integer getScapeRoomId() {
        return scapeRoomId;
    }


    public DifficultLevel getDifficultLevel() {
        return difficultLevel;
    }


    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}