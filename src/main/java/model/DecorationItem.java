package model;

import enums.Theme;

import java.math.BigDecimal;

public class DecorationItem extends Item {
    private Integer roomId;
    private Material material;
    private Theme theme;
    private BigDecimal price;


    public DecorationItem(Integer roomId,String name, Material material, Theme theme, String description, BigDecimal price) {
        super(name, description);
        this.roomId = roomId;
        this.material = material;
        this.theme = theme;
        this.price = price;
    }

    public DecorationItem( int id,Integer roomId, String name, Material material, Theme theme, String description, BigDecimal price) {
        super(id, name, description);
        this.roomId = roomId;
        this.material = material;
        this.theme = theme;
        this.price = price;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public enum Material {
        WOOD, METAL, PLASTIC, PAPER, CRYSTAL
    }
}