package manager;

import observer.NotificationService;

import model.Room;
import enums.Theme;
import repository.dao.RoomDAO;
import repository.dao.RoomDAOSQL;
import util.InputHandler;

import java.util.List;

public class RoomManager {
    private final RoomDAO dao = new RoomDAOSQL();

    public void createRoom() {
        int scapeRoomId = InputHandler.readInt("Enter ScapeRoom ID: ");
        Room.DifficultLevel level = InputHandler.readEnum(Room.DifficultLevel.class, "Enter difficulty level: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme: ");

        Room room = new Room(scapeRoomId, level, theme);
        try {
            dao.insert(room);
            NotificationService.getInstance()
                    .notifyObservers("Created new Room with theme: " + theme +" and difficulty: "+level);
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
        List<Room> rooms = dao.findAll();
        if (rooms.isEmpty()) {
            System.out.println("There are no Rooms available to update.");
            return;
        }
        System.out.println("Available Rooms:");
        rooms.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getTheme()+" → " + r.getDifficultLevel()));
        int id = InputHandler.readOptionalInt("Enter Room ID to update (or press Enter to skip): ");
        int scapeRoomId = InputHandler.readInt("Enter new ScapeRoom ID: ");
        Room.DifficultLevel level = InputHandler.readEnum(Room.DifficultLevel.class, "Enter new difficulty level: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter new theme: ");

        Room room = new Room(scapeRoomId, level, theme);
        room.setId(id);
        try {
            dao.update(room);
            NotificationService.getInstance()
                    .notifyObservers("Updated Room with theme: " + theme +" and difficulty: "+level);
        } catch (Exception e) {
            System.out.println("Error updating Room: " + e.getMessage());
        }
    }

    public void deleteRoom() {
        List<Room> rooms = dao.findAll();
        if (rooms.isEmpty()) {
            System.out.println("There are no Rooms available to delete.");
            return;
        }
        System.out.println("Available Rooms:");
        rooms.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getTheme()+" → " + r.getDifficultLevel()));
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