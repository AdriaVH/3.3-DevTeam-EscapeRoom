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
        Integer roomId = InputHandler.readValidOrSkipId(roomDao::findById,"Enter Room ID (press '0' to leave it WITHOUT Room assigned): ");
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

        int id = InputHandler.readValidId(dao::findById,"Enter id: ");
        String name = InputHandler.readOptionalString("Enter new name (or press Enter to skip): ");
        int roomID = InputHandler.readValidOrSkipId(roomDao::findById,"Enter new room ID (press 'Enter' to skip OR '0' to leave it WITHOUT Room assigned)");
        Theme theme = InputHandler.readOptionalEnum(Theme.class, "Enter new theme (or press Enter to skip): ");
        String description = InputHandler.readOptionalString("Enter a new description (or press Enter to skip): ");

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

        int id = InputHandler.readValidId(dao::findById,"Enter Enigma ID to delete: ");
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
}