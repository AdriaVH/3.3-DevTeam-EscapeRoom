package manager;

import observer.NotificationService;
import model.ScapeRoom;
import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;
import util.InputHandler;

public class ScapeRoomManager {
    private final ScapeRoomDAO dao = new ScapeRoomDAOSQL();

    public void createScapeRoom() {
        String name = InputHandler.readString("Enter ScapeRoom name: ");
        ScapeRoom obj = new ScapeRoom(name);
        try {
            dao.insert(obj);
            NotificationService.getInstance()
                    .notifyObservers("Created new EscapeRoom: " + name);
        } catch (Exception e) {
            System.out.println("Error creating EscapeRoom: " + e.getMessage());
        }
    }

    public void listScapeRooms() {
        System.out.printf("%-4s %-30s%n", "ID", "Name");
        System.out.println("─".repeat(35)); // separator line

        dao.findAll().forEach(s ->
                System.out.printf("%-4d %-30s%n",
                        s.getId(),
                        s.getName())
        );
    }

    public void updateScapeRoom() {
        listScapeRooms();

        int id = readValidScaperoomId("Enter ScapeRoom ID to update: ");
        String name = InputHandler.readString("Enter new name: ");
        ScapeRoom obj = new ScapeRoom(name);
        obj.setId(id);
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

        int id = readValidScaperoomId("Enter ScapeRoom ID to delete: ");
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

    private int readValidScaperoomId(String message) {
        while (true) {
            int id = InputHandler.readInt(message);
            if (dao.findById(id) != null) {
                return id;
            }
            System.out.println("❌ No Scaperoom exists with ID: " + id + ". Please try again.");
        }
    }
}