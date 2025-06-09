package manager;

import observer.NotificationService;

import enums.Theme;
import model.DecorationItem;
import repository.dao.DecorationItemDAO;
import repository.dao.DecorationItemDAOSQL;
import util.InputHandler;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DecorationItemManager {
    private final DecorationItemDAO dao = new DecorationItemDAOSQL();

    public void createDecorationItem() {
        String name = InputHandler.readString("Enter a name:");
        Integer roomId = InputHandler.readOptionalInt("Enter roomId (or press Enter to skip): ");
        DecorationItem.Material material = InputHandler.readEnum(DecorationItem.Material.class, "Enter material");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme");
        String description = InputHandler.readString("Enter description");
        BigDecimal price = InputHandler.readBigDecimal("Enter price: ");

        DecorationItem item = new DecorationItem(roomId,name,material,theme,description,price);
        try {
            dao.insert(item);
            NotificationService.getInstance()
                    .notifyObservers("Created new DecorationItem: " + name);
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
        List<DecorationItem> decorations = dao.findAll();
        if (decorations.isEmpty()) {
            System.out.println("There are no DecorationItems available to update.");
            return;
        }
        System.out.println("Available DecorationItems:");
        decorations.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter DecorationItem ID to update: ");
        String name = InputHandler.readString("Enter a new name:");
        Integer roomId = InputHandler.readOptionalInt("Enter a new roomId (or press Enter to skip): ");
        DecorationItem.Material material = InputHandler.readEnum(DecorationItem.Material.class, "Enter a new material");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter a new theme");
        String description = InputHandler.readString("Enter a new description");
        BigDecimal price = InputHandler.readBigDecimal("Enter a new price: ");

        DecorationItem item = new DecorationItem(roomId,name,material,theme,description,price);
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
        List<DecorationItem> decorations = dao.findAll();
        if (decorations.isEmpty()) {
            System.out.println("There are no DecorationItems available to delete.");
            return;
        }
        System.out.println("Available DecorationItems:");
        decorations.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter DecorationItem ID to delete: ");
        try {
            dao.delete(id);
            String name = decorations.stream()
                    .filter(r -> r.getId() == id)
                    .map(DecorationItem::getName)
                    .collect(Collectors.joining());
            NotificationService.getInstance()
                    .notifyObservers("Deleted DecorationItem: " + name);
        } catch (Exception e) {
            System.out.println("Error deleting DecorationItem: " + e.getMessage());
        }
    }
}