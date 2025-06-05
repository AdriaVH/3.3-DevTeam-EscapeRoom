package model;

import enums.Material;
import enums.Theme;

import java.math.BigDecimal;

public class DecorationItem extends Item {
    private int roomId;
    private Material material;
    private Theme theme;
    private BigDecimal price;

    public DecorationItem() {
        super();
    }

    public DecorationItem(String name, String description, int roomId, Material material, Theme theme, BigDecimal price) {
        super(name, description);
        this.roomId = roomId;
        this.material = material;
        this.theme = theme;
        this.price = price;
    }

    public DecorationItem(int roomId, String name, String description, int id, Material material, Theme theme, BigDecimal price) {
        super(id, name, description);
        this.roomId = roomId;
        this.material = material;
        this.theme = theme;
        this.price = price;
    }

    public int getRoomId() {
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
}