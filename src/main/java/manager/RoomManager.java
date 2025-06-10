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
        Integer scapeRoomId = InputHandler.readValidId(scapeRoomDao::findById,"Enter ScapeRoom ID to assign it to or empty for NO assignation: ");

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

        int id = InputHandler.readValidId(dao::findById,"Enter Room ID to update: ");
        Integer scapeRoomId = InputHandler.readValidOrSkipId(scapeRoomDao::findById,"Enter ScapeRoom new ID (press 'Enter' to skip OR '0' to leave it WITHOUT ScapeRoom assigned): ");
        Room.DifficultLevel level = InputHandler.readOptionalEnum(Room.DifficultLevel.class, "Enter new difficulty level (or press Enter to skip): ");
        Theme theme = InputHandler.readOptionalEnum(Theme.class, "Enter new theme (or press Enter to skip): ");

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

        int id = InputHandler.readValidId(dao::findById,"Enter Room ID to delete: ");
        try {
            Room room = dao.findById(id);
            dao.delete(room.getId());
            NotificationService.getInstance()
                    .notifyObservers("Deleted Room: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting Room: " + e.getMessage());
        }
    }
}