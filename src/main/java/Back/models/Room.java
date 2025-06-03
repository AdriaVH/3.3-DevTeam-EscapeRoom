package Back.models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private int scapeRoom_Id;
    private Difficult difficult;
    private Theme theme;
    private List<Item> items;

    //Cal revisar si cal o no Builder
    private Room(Builder builder) {
        this.id = builder.id;
        this.scapeRoom_Id = builder.scapeRoomId;
        this.difficult = builder.difficult;
        this.theme = builder.theme;
        this.items= new ArrayList<>();
    }

    //ENUM ENCAPSULAT
    public enum Difficult {
        EASY, MEDIUM , HARD;
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public void setDifficult_id(Difficult difficult) {
        this.difficult = difficult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScapeRoom_Id() {
        return scapeRoom_Id;
    }

    public void setScapeRoom_Id(int scapeRoom_Id) {
        this.scapeRoom_Id = scapeRoom_Id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public static class Builder {

        private int id;
        private int scapeRoomId;
        public Difficult difficult;
        private Theme theme;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withScapeRoomId(int scapeRoomId) {
            this.scapeRoomId = scapeRoomId;
            return this;
        }

        public Builder withDifficultId(Difficult difficult) {
            this.difficult= difficult;
            return this;
        }

        public Builder withTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}