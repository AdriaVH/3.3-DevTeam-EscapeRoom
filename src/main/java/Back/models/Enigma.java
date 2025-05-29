package Back.models;

public class Enigma extends Item {
    private int id;
    private int item_Id;
    private Theme theme;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_Id() {
        return item_Id;
    }

    public void setItem_id(int item_id) {
        this.item_Id = item_id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    private Enigma(Builder builder) {
        this.id = builder.id;
        this.item_Id = builder.item_Id;
        this.theme = builder.theme;
    }

    public static class Builder {
        private int id;
        private int item_Id;
        private Theme theme;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withItemId(int itemId) {
            this.item_Id = itemId;
            return this;
        }

        public Builder withTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public Enigma build() {
            return new Enigma(this);
        }
    }
}



