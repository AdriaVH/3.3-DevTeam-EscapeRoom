package Back.models;

import front.Menus.reusableMenuTest.MenuOption;

import java.math.BigDecimal;
import java.util.List;

import static APP.Main.scanner;

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
    @Override
    public List getListModifyMenu(){
        List list = List.of(
                new MenuOption("Modify name", () -> recieveName()),
                new MenuOption("Modify description", () -> recieveDescription()),
                new MenuOption("Back", () -> {}));

        return list;
    };

    @Override
    public String toString() {
        return super.toString() + "material=" + material +
                ", theme=" + theme +
                ", price=" + price;
    }

    public void recieveName(){
        System.out.println("Introduce the new name: ");
        String name = input();
        super.setName(name);
    }
    public void recieveDescription(){
        System.out.println("Introduce the new description: ");
        String desc = input();
        super.setDescription(desc);
    }
    public String input (){
        String result;
        result = scanner.next();
        validator(result);
        return result;
    }
    public <T> void validator(T toValidate) {
    // validation methods
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

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
