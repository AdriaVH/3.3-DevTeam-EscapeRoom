package manager;

import observer.NotificationService;
import enums.Theme;
import model.DecorationItem;
import repository.dao.DecorationItemDAO;
import repository.dao.DecorationItemDAOSQL;
import repository.dao.RoomDAOSQL;
import util.InputHandler;
import java.math.BigDecimal;

public class DecorationItemManager {
    private final DecorationItemDAO dao = new DecorationItemDAOSQL();
    private final RoomDAOSQL roomDao = new RoomDAOSQL();

    public void createDecorationItem() {
        String name = InputHandler.readString("Enter a name: ");
        Integer roomId = readValidOrSkipRoomId("Enter Room ID (or press Enter to skip): ");
        DecorationItem.Material material = InputHandler.readEnum(DecorationItem.Material.class, "Enter material: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme: ");
        String description = InputHandler.readString("Enter description: ");
        BigDecimal price = InputHandler.readBigDecimal("Enter price: ");

        DecorationItem item = new DecorationItem(roomId, name, material, theme, description, price);

        try {
            dao.insert(item);
            NotificationService.getInstance()
                    .notifyObservers("✅ Created new DecorationItem: " + name);
        } catch (Exception e) {
            System.out.println("❌ Error creating DecorationItem: " + e.getMessage());
        }
    }

    public void listDecorationItems() {
        System.out.printf("%-4s %-20s %-10s %-12s %-20s %-7s %-60s%n",
                "ID", "Name", "Room ID", "Material", "Theme", "Price", "Description");
        System.out.println("─".repeat(140)); // nice separator

        dao.findAll().forEach(i -> {
            String description = i.getDescription();
            if (description.length() > 57) {
                description = description.substring(0, 54) + "...";
            }

            System.out.printf("%-4d %-20s %-10d %-12s %-20s $%-6.2f %-60s%n",
                    i.getId(), i.getName(), i.getRoomId(), i.getMaterial(),
                    i.getTheme(), i.getPrice(), description);
        });
    }

    public void updateDecorationItem() {
        listDecorationItems();

        int id = readValidDecorationItemId("Enter DecorationItem ID to update: ");
        String name = InputHandler.readString("Enter a new name:");
        Integer roomId = readValidOrSkipRoomId("Enter a new roomId (or press Enter to skip): ");
        DecorationItem.Material material = InputHandler.readEnum(DecorationItem.Material.class, "Enter a new material");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter a new theme: ");
        String description = InputHandler.readString("Enter a new description: ");
        BigDecimal price = InputHandler.readBigDecimal("Enter a new price: ");

        DecorationItem item = new DecorationItem(id, roomId, name, material, theme, description, price);
        try {
            dao.update(item);
            NotificationService.getInstance()
                    .notifyObservers("Updated DecorationItem: " + item.getId());
        } catch (Exception e) {
            System.out.println("Error updating DecorationItem: " + e.getMessage());
        }
    }

    public void deleteDecorationItem() {
        listDecorationItems();

        int id = readValidDecorationItemId("Enter DecorationItem ID to delete: ");
        try {
            DecorationItem decoItem = dao.findById(id);
            String name = decoItem.getName();
            dao.delete(decoItem.getId());
            NotificationService.getInstance()
                    .notifyObservers("Deleted DecorationItem: " + name);
        } catch (Exception e) {
            System.out.println("Error deleting DecorationItem: " + e.getMessage());
        }
    }

    private Integer readValidOrSkipRoomId(String message) {
        while (true) {
            Integer roomId = InputHandler.readOptionalInt(message);
            if (roomId == null || roomDao.findById(roomId) != null) {
                return roomId; // Valid or skipped
            }
            System.out.println("❌ No Room exists with ID: " + roomId + ". Please try again.");
        }
    }

    private int readValidDecorationItemId(String message) {
        while (true) {
            int id = InputHandler.readInt(message);
            if (dao.findById(id) != null) {
                return id;
            }
            System.out.println("❌ No DecorationItem exists with ID: " + id + ". Please try again.");
        }
    }
}