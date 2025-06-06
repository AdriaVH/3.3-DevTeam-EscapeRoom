package manager;

import observer.NotificationService;

import enums.Theme;
import model.Enigma;
import repository.dao.EnigmaDAO;
import repository.dao.EnigmaDAOSQL;
import util.InputHandler;

import java.util.List;
import java.util.stream.Collectors;

public class EnigmaManager {
    private final EnigmaDAO dao = new EnigmaDAOSQL();

    public void createEnigma() {
        String name = InputHandler.readString("Enter name: ");
        int roomID = InputHandler.readInt("Enter room ID");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme");
        String description = InputHandler.readString("Enter a description:");


        Enigma enigma = new Enigma(roomID, name, description,theme);
        try {
            dao.insert(enigma);
            NotificationService.getInstance()
                    .notifyObservers("Created new Enigma: " + name);
        } catch (Exception e) {
            System.out.println("Error creating Enigma: " + e.getMessage());
        }
    }

    public void listEnigmas() {
        dao.findAll().forEach(e ->
                System.out.println("ID: " + e.getId() +
                        " | name: " + e.getName() +
                        " | room id: " + e.getRoomId() +
                        " | theme: " + e.getTheme() +
                        " | description: " + e.getDescription()
        ));
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
        int roomID = InputHandler.readOptionalInt("Enter new room ID (or press Enter to skip)");
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
}