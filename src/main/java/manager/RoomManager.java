package manager;

import observer.NotificationService;

import model.Room;
import enums.DifficultLevel;
import enums.Theme;
import repository.dao.RoomDAO;
import repository.dao.RoomDAOSQL;
import util.InputHandler;

public class RoomManager {
    private final RoomDAO dao = new RoomDAOSQL();

    public void createRoom() {
        int scapeRoomId = InputHandler.readInt("Enter ScapeRoom ID: ");
        DifficultLevel level = InputHandler.readEnum(DifficultLevel.class, "Enter difficulty level: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme: ");

        Room room = new Room(scapeRoomId, level, theme);
        try {
            dao.insert(room);
            NotificationService.getInstance()
                    .notifyObservers("Created new Room: " + room.getId());
        } catch (Exception e) {
            System.out.println("Error creating Room: " + e.getMessage());
        }
    }

    public void listRooms() {
        dao.findAll().forEach(r ->
                System.out.println("ID: " + r.getId() +
                        " | ScapeRoom ID: " + r.getScapeRoomId() +
                        " | Level: " + r.getDifficultLevel() +
                        " | Theme: " + r.getTheme())
        );
    }

    public void updateRoom() {
        int id = InputHandler.readInt("Enter Room ID to update: ");
        int scapeRoomId = InputHandler.readInt("Enter new ScapeRoom ID: ");
        DifficultLevel level = InputHandler.readEnum(DifficultLevel.class, "Enter new difficulty level: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter new theme: ");

        Room room = new Room(scapeRoomId, level, theme);
        room.setId(id);
        try {
            dao.update(room);
            NotificationService.getInstance()
                    .notifyObservers("Updated Room: " + room.getId());
        } catch (Exception e) {
            System.out.println("Error updating Room: " + e.getMessage());
        }
    }

    public void deleteRoom() {
        int id = InputHandler.readInt("Enter Room ID to delete: ");
        try {
            dao.delete(id);
            NotificationService.getInstance()
                    .notifyObservers("Deleted Room: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting Room: " + e.getMessage());
        };
    }
}