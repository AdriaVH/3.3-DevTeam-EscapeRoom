package manager;

import observer.NotificationService;
import model.ScapeRoom;
import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;
import util.InputHandler;

import java.math.BigDecimal;

public class ScapeRoomManager {
    private final ScapeRoomDAO dao = new ScapeRoomDAOSQL();

    public void createScapeRoom() {
        String name = InputHandler.readString("Enter ScapeRoom name: ");
        BigDecimal ticketPrice = InputHandler.readBigDecimal("Enter ScapeRoom ticket price: ");
        ScapeRoom obj = new ScapeRoom(name,ticketPrice);
        try {
            dao.insert(obj);
            NotificationService.getInstance()
                    .notifyObservers("Created new EscapeRoom: " + name + "." + ticketPrice + "€");
        } catch (Exception e) {
            System.out.println("Error creating EscapeRoom: " + e.getMessage());
        }
    }

    public void listScapeRooms() {
        System.out.printf("%-4s %-30s %-10s%n", "ID", "Name", "Price");
        System.out.println("─".repeat(46)); // Adjusted separator line length

        dao.findAll().forEach(s ->
                System.out.printf("%-4d %-30s %-10s%n",
                        s.getId(),
                        s.getName(),
                        s.getTicketPrice() != null ? s.getTicketPrice().toString() : "N/A")
        );

    }

    public void updateScapeRoom() {
        listScapeRooms();

        int id = InputHandler.readValidId(dao::findById,"Enter ScapeRoom ID to update: ");
        String name = InputHandler.readOptionalString("Enter new name (or press Enter to skip): ");
        BigDecimal price = InputHandler.readOptionalBigDecimal("Enter new price (or press Enter to skip): ");
        ScapeRoom obj = new ScapeRoom(id,name, price);

        try {
            dao.update(obj);
            NotificationService.getInstance()
                    .notifyObservers("Updated EscapeRoom: " + name);
        } catch (Exception e) {
            System.out.println("Error updating EscapeRoom: " + e.getMessage());
        }
    }

    public void deleteScapeRoom() {
        listScapeRooms();

        int id = InputHandler.readValidId(dao::findById,"Enter ScapeRoom ID to delete: ");
        try {
            ScapeRoom scapeRoom = dao.findById(id);
            String name = scapeRoom.getName();
            dao.delete(scapeRoom.getId());
            NotificationService.getInstance().notifyObservers("Deleted EscapeRoom: " + name);
        } catch (
                RuntimeException e) {
            System.out.println("Error deleting EscapeRoom with id = " + e.getMessage());
        }
    }
}