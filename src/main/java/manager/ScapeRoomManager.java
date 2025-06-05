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
                    .notifyObservers("Created new EscapeRoom: " + obj.getId());
        } catch (Exception e) {
            System.out.println("Error creating EscapeRoom: " + e.getMessage());
        }
    }

    public void listScapeRooms() {
        dao.findAll().forEach(s ->
                System.out.println("ID: " + s.getId() + " | Name: " + s.getName())
        );
    }

    public void updateScapeRoom() {
        int id = InputHandler.readInt("Enter ScapeRoom ID to update: ");
        String name = InputHandler.readString("Enter new name: ");
        ScapeRoom obj = new ScapeRoom(name);
        obj.setId(id);
        try {
            dao.update(obj);
            NotificationService.getInstance()
                    .notifyObservers("Updated EscapeRoom: " + obj.getId());
        } catch (Exception e) {
            System.out.println("Error updating EscapeRoom: " + e.getMessage());
        }
    }

    public void deleteScapeRoom() {
        int id = InputHandler.readInt("Enter ScapeRoom ID to delete: ");
        try {
            dao.delete(id);
            NotificationService.getInstance()
                    .notifyObservers("Deleted EscapeRoom: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting EscapeRoom with id = : " + e.getMessage());
        }
    }
}