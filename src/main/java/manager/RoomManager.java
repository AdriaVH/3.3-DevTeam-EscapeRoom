package manager;

import observer.NotificationService;
import model.Room;
import enums.Theme;
import repository.dao.RoomDAO;
import repository.dao.RoomDAOSQL;
import repository.dao.ScapeRoomDAOSQL;
import util.InputHandler;

public class RoomManager {
    private final RoomDAO dao = new RoomDAOSQL();
    private final ScapeRoomDAOSQL scapeRoomDao = new ScapeRoomDAOSQL();

    public void createRoom() {
        Integer scapeRoomId = readValidOrSkipScapeRoomId("Assign a ScapeRoom using its ID or leave empty for NO assignation: ");

        Room.DifficultLevel level = InputHandler.readEnum(Room.DifficultLevel.class, "Enter difficulty level: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme: ");

        Room room = new Room(scapeRoomId, level, theme);

        try {
            dao.insert(room);
            NotificationService.getInstance().notifyObservers(
                    "✅ Created new Room with theme: " + theme + " and difficulty: " + level);
        } catch (Exception e) {
            System.out.println("❌ Error creating Room: " + e.getMessage());
        }
    }


    public void listRooms() {
        System.out.printf("%-4s %-14s %-10s %-20s%n",
                "ID", "ScapeRoom ID", "Level", "Theme");
        System.out.println("─".repeat(60));

        dao.findAll().forEach(r ->
                System.out.printf("%-4d %-14s %-10s %-20s%n",
                        r.getId(),
                        r.getScapeRoomId() != null ? r.getScapeRoomId().toString() : "N/A",
                        r.getDifficultLevel(),
                        r.getTheme())
        );
    }

    public void updateRoom() {
        listRooms();

        int id = readValidRoomId("Enter Room ID to update: ");
        int scapeRoomId = readValidOrSkipScapeRoomId("Enter new ScapeRoom ID: ");
        Room.DifficultLevel level = InputHandler.readEnum(Room.DifficultLevel.class, "Enter new difficulty level: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter new theme: ");

        Room room = new Room(id, scapeRoomId, level, theme);
        try {
            dao.update(room);
            NotificationService.getInstance()
                    .notifyObservers("Updated Room with theme: " + theme + " and difficulty: " + level);
        } catch (Exception e) {
            System.out.println("Error updating Room: " + e.getMessage());
        }
    }

    public void deleteRoom() {
        listRooms();

        int id = readValidRoomId("Enter Room ID to delete: ");
        try {
            Room room = dao.findById(id);
            dao.delete(room.getId());
            NotificationService.getInstance()
                    .notifyObservers("Deleted Room: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting Room: " + e.getMessage());
        }
    }

    private Integer readValidOrSkipScapeRoomId(String prompt) {
        while (true) {
            Integer scapeRoomId = InputHandler.readOptionalInt(prompt);
            if (scapeRoomId == null || scapeRoomDao.findById(scapeRoomId) != null) {
                return scapeRoomId;
            }
            System.out.println("❌ No ScapeRoom exists with ID: " + scapeRoomId + ". Please try again.");
        }
    }

    private int readValidRoomId(String message) {
        while (true) {
            int id = InputHandler.readInt(message);
            if (dao.findById(id) != null) {
                return id;
            }
            System.out.println("❌ No Room exists with ID: " + id + ". Please try again.");
        }
    }
}