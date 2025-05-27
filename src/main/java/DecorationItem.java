public class DecorationItem {
    public String name;
    public String description;
    public Material material;
    public Theme theme;
    public double price;

    public DecorationItem(String name, String description, Material material, Theme theme, double price) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.theme = theme;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Material getMaterial() {
        return material;
    }

    public Theme getTheme() {
        return theme;
    }

    public double getPrice() {
        return price;
    }
}
