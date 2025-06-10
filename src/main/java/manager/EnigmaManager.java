package manager;

import observer.NotificationService;
import enums.Theme;
import model.Enigma;
import repository.dao.EnigmaDAO;
import repository.dao.EnigmaDAOSQL;
import repository.dao.RoomDAOSQL;
import util.InputHandler;

public class EnigmaManager {
    private final EnigmaDAO dao = new EnigmaDAOSQL();
    private final RoomDAOSQL roomDao = new RoomDAOSQL();

    public void createEnigma() {
        String name = InputHandler.readString("Enter name: ");
        Integer roomId = readValidOrSkipRoomId("Enter Room ID (or press Enter to skip): ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme");
        String description = InputHandler.readString("Enter a description:");

        Enigma enigma = new Enigma(roomId, name, description,theme);
        try {
            dao.insert(enigma);
            NotificationService.getInstance()
                    .notifyObservers("Created new Enigma: " + name);
        } catch (Exception e) {
            System.out.println("Error creating Enigma: " + e.getMessage());
        }
    }

    public void listEnigmas() {
        System.out.printf("%-4s %-25s %-10s %-20s %-60s%n",
                "ID", "Name", "Room ID", "Theme", "Description");
        System.out.println("─".repeat(120)); // separator line

        dao.findAll().forEach(e -> {
            String description = e.getDescription();
            if (description.length() > 57) {
                description = description.substring(0, 54) + "...";
            }

            System.out.printf("%-4d %-25s %-10d %-20s %-60s%n",
                    e.getId(),
                    e.getName(),
                    e.getRoomId(),
                    e.getTheme(),
                    description);
        });
    }


    public void updateEnigma() {
        listEnigmas();

        int id = readValidEnigmaId("Enter id: ");
        String name = InputHandler.readString("Enter new name");
        int roomID = readValidOrSkipRoomId("Enter new room ID (or press Enter to skip)");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter new theme");
        String description = InputHandler.readString("Enter a new description:");

        Enigma enigma = new Enigma(id, roomID, name, theme, description);
        try {
            dao.update(enigma);
            NotificationService.getInstance()
                    .notifyObservers("Updated Enigma: " + name);
        } catch (Exception e) {
            System.out.println("Error updating Enigma: " + e.getMessage());
        }
    }

    public void deleteEnigma() {
        listEnigmas();

        int id = readValidEnigmaId("Enter Enigma ID to delete: ");
        try {
            Enigma enigma = dao.findById(id);
            String name = enigma.getName();
            dao.delete(enigma.getId());
            NotificationService.getInstance()
                    .notifyObservers("Deleted Enigma: " + name);
        } catch (Exception e) {
            System.out.println("Error deleting Enigma: " + e.getMessage());
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
    private int readValidEnigmaId(String message) {
        while (true) {
            int id = InputHandler.readInt(message);
            if (dao.findById(id) != null) {
                return id;
            }
            System.out.println("❌ No Enigma exists with ID: " + id + ". Please try again.");
        }
    }
}