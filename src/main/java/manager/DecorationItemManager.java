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
        Integer roomId = InputHandler.readValidOrSkipId(roomDao::findById,"Enter Room new ID (press '0' to leave it WITHOUT Room assigned): ");
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

            System.out.printf("%-4d %-20s %-10d %-12s %-20s %-6.2f€ %-60s%n",
                    i.getId(), i.getName(), i.getRoomId(), i.getMaterial(),
                    i.getTheme(), i.getPrice(), description);
        });
        System.out.println("-".repeat(140)); // footer separator
    }

    public void updateDecorationItem() {
        listDecorationItems();

        int id = InputHandler.readValidId(dao::findById,"Enter DecorationItem ID to update: ");
        String name = InputHandler.readOptionalString("Enter a new name (or press Enter to skip): ");
        Integer roomId = InputHandler.readValidOrSkipId(roomDao::findById,"Enter Room with new ID (press 'Enter' to skip OR '0' to leave it WITHOUT Room assigned): ");
        DecorationItem.Material material = InputHandler.readOptionalEnum(DecorationItem.Material.class, "Enter a new material (or press Enter to skip) : ");
        Theme theme = InputHandler.readOptionalEnum(Theme.class, "Enter a new theme (or press Enter to skip): ");
        String description = InputHandler.readOptionalString("Enter a new description (or press Enter to skip): ");
        BigDecimal price = InputHandler.readOptionalBigDecimal("Enter a new price (or press Enter to skip): ");

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

        Integer id = InputHandler.readValidId(dao::findById,"Enter DecorationItem ID to delete: ");
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

}