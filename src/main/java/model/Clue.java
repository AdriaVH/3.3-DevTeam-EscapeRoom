package model;

import java.math.BigDecimal;

public class Clue extends Item {
    private int id;
    private int enigma_Id;
    private int item_Id;
    private Theme theme;
    private BigDecimal price;


    public int getEnigma_Id() {
        return enigma_Id;
    }

    public void setEnigma_Id(int enigma_Id) {
        this.enigma_Id = enigma_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_Id() {
        return item_Id;
    }

    public void setItem_Id(int item_Id) {
        this.item_Id = item_Id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    private Clue (Builder builder) {
        this.id=builder.id;
        this.enigma_Id=builder.enigma_Id;
        this.item_Id=builder.item_Id;
        this.theme=builder.theme;
        this.price=builder.price;

    }

    private static class Builder{
        private int id;
        private int enigma_Id;
        private int item_Id;
        private Theme theme;
        private BigDecimal price;

        public Builder withId(int id) {
            this.id = id; return this; }

        public Builder withEnigmaId(int enigmaId) {
            this.enigma_Id = enigmaId; return this; }

        public Builder withItemId(int itemId) {
            this.item_Id = itemId; return this; }

        public Builder withPrice(BigDecimal price) {
            this.price = price; return this; }

        public Builder withTheme(Theme theme) {
            this.theme = theme; return this; }

        public Clue build() {
            return new Clue(this); }
    }
}
