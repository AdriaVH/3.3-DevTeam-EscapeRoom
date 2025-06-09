package manager;

import observer.NotificationService;

import enums.Theme;
import model.Enigma;
import repository.dao.EnigmaDAO;
import repository.dao.EnigmaDAOSQL;
import repository.dao.RoomDAOSQL;
import util.InputHandler;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Enigma> enigmas = dao.findAll();
        if (enigmas.isEmpty()) {
            System.out.println("There are no Enigmas available to update.");
            return;
        }
        System.out.println("Available Enigmas:");
        enigmas.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter id: ");
        String name = InputHandler.readString("Enter new name");
        int roomID = readValidOrSkipRoomId("Enter new room ID (or press Enter to skip)");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter new theme");
        String description = InputHandler.readString("Enter a new description:");

        Enigma enigma = new Enigma(roomID, name, description, theme);
        enigma.setId(id);
        try {
            dao.update(enigma);
            NotificationService.getInstance()
                    .notifyObservers("Updated Enigma: " + name);
        } catch (Exception e) {
            System.out.println("Error updating Enigma: " + e.getMessage());
        }
    }

    public void deleteEnigma() {
        List<Enigma> enigmas = dao.findAll();
        if (enigmas.isEmpty()) {
            System.out.println("There are no Enigmas available to delete.");
            return;
        }
        System.out.println("Available Rooms:");
        enigmas.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter Enigma ID to delete: ");
        try {
            dao.delete(id);
            String name = enigmas.stream()
                    .filter(r -> r.getId() == id)
                    .map(Enigma::getName)
                    .collect(Collectors.joining());
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
}