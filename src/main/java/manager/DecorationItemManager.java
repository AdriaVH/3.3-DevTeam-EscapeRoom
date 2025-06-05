package manager;
import observer.NotificationService;

import enums.Material;
import enums.Theme;
import model.DecorationItem;
import repository.dao.DecorationItemDAO;
import repository.dao.DecorationItemDAOSQL;
import util.InputHandler;

import java.math.BigDecimal;

public class DecorationItemManager {
    private final DecorationItemDAO dao = new DecorationItemDAOSQL();

    public void createDecorationItem() {
        String name = InputHandler.readString("Enter a name:");
        Integer roomId = InputHandler.readOptionalInt("Enter roomId (or press Enter to skip): ");
        Material material = InputHandler.readEnum(Material.class, "Enter material");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme");
        String description = InputHandler.readString("Enter description");
        BigDecimal price = InputHandler.readBigDecimal("Enter price: ");

        DecorationItem item = new DecorationItem(name,description,roomId,material,theme,price);
        try {
            dao.insert(item);
            NotificationService.getInstance()
                    .notifyObservers("Created new DecorationItem: " + item.getId());
        } catch (Exception e) {
            System.out.println("Error creating DecorationItem: " + e.getMessage());
        }
    }

    public void listDecorationItems() {
        dao.findAll().forEach(i ->
                System.out.println("ID: " + i.getId() +
                        " | name: " + i.getName() +
                        " | room_id: " + i.getRoomId() +
                        " | material: " + i.getMaterial() +
                        " | theme: " + i.getTheme() +
                        " | description: " + i.getDescription() +
                        " | price: " + i.getPrice())
        );
    }

    public void updateDecorationItem() {
        int id = InputHandler.readInt("Enter DecorationItem ID to update: ");
        String name = InputHandler.readString("Enter a new name:");
        Integer roomId = InputHandler.readOptionalInt("Enter a new roomId (or press Enter to skip): ");
        Material material = InputHandler.readEnum(Material.class, "Enter a new material");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter a new theme");
        String description = InputHandler.readString("Enter a new description");
        BigDecimal price = InputHandler.readBigDecimal("Enter a new price: ");

        DecorationItem item = new DecorationItem(name,description,roomId,material,theme,price);
        item.setId(id);
        try {
            dao.update(item);
            NotificationService.getInstance()
                    .notifyObservers("Updated DecorationItem: " + item.getId());
        } catch (Exception e) {
            System.out.println("Error updating DecorationItem: " + e.getMessage());
        }
    }

    public void deleteDecorationItem() {
        int id = InputHandler.readInt("Enter DecorationItem ID to delete: ");
        try {
            dao.delete(id);
            NotificationService.getInstance()
                    .notifyObservers("Deleted DecorationItem: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting DecorationItem: " + e.getMessage());
        }
    }
}