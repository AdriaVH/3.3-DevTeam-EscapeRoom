package Back.models;

import java.math.BigDecimal;

public class DecorationItem extends Item {
    private Material material;
    private Theme theme;
    private BigDecimal price;

    public DecorationItem(int roomId, String name, String description, Material material, Theme theme, BigDecimal price) {
        super(roomId, name, description);
        this.material = material;
        this.theme = theme;
        this.price = price;
    }

    //ENUM ENCAPSULAT
    public enum Material {
        WOOD, METAL, PLASTIC, PAPER, CRYSTAL;
    }

    public Material getMaterial() {
        return material;
    }

    public Theme getTheme() {
        return theme;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
