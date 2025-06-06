package manager;

import observer.NotificationService;

import enums.Theme;
import model.Enigma;
import repository.dao.EnigmaDAO;
import repository.dao.EnigmaDAOSQL;
import util.InputHandler;

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
                    .notifyObservers("Created new Enigma: " + enigma.getId());
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
        int id = InputHandler.readInt("Enter id: ");
        String name = InputHandler.readString("Enter new name");
        int roomID = InputHandler.readInt("Enter new room ID");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter new theme");
        String description = InputHandler.readString("Enter a new description:");

        Enigma enigma = new Enigma(roomID, name, description, theme);
        enigma.setId(id);
        try {
            dao.update(enigma);
            NotificationService.getInstance()
                    .notifyObservers("Updated Enigma: " + enigma.getId());
        } catch (Exception e) {
            System.out.println("Error updating Enigma: " + e.getMessage());
        }
    }

    public void deleteEnigma() {
        int id = InputHandler.readInt("Enter Enigma ID to delete: ");
        try {
            dao.delete(id);
            NotificationService.getInstance()
                    .notifyObservers("Deleted Enigma: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting Enigma: " + e.getMessage());
        }
    }
}