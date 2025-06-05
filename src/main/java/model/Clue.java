package model;

import enums.Theme;

import java.math.BigDecimal;

public class Clue extends Item {
    private Integer enigmaId;
    private Theme theme;
    private BigDecimal price;


    public Clue(String name, String description, Integer enigmaId, Theme theme, BigDecimal price) {
        super(name, description);
        this.enigmaId = enigmaId;
        this.theme = theme;
        this.price = price;
    }

    public Clue(int id, Integer enigmaId, String name, Theme theme, String description, BigDecimal price) {
        super(id, name, description);
        this.enigmaId = enigmaId;
        this.theme = theme;
        this.price = price;
    }

    public Integer getEnigmaId() {
        return enigmaId;
    }


    public Theme getTheme() {
        return theme;
    }


    public BigDecimal getPrice() {
        return price;
    }

}
