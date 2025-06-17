package manager;

import observer.NotificationService;
import enums.Theme;
import model.Clue;
import repository.dao.ClueDAO;
import repository.dao.ClueDAOSQL;
import repository.dao.EnigmaDAO;
import repository.dao.EnigmaDAOSQL;
import util.InputHandler;
import java.math.BigDecimal;

public class ClueManager {
    private final ClueDAO dao = new ClueDAOSQL();
    private final EnigmaDAO enigmaDao= new EnigmaDAOSQL();

    public void createClue() {
        String name = InputHandler.readString("Enter a name:");
        Integer enigmaId = InputHandler.readValidId(enigmaDao::findById,"Enter Enigma ID: ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme");
        String description = InputHandler.readString("Enter description");
        BigDecimal price = InputHandler.readBigDecimal("Enter price: ");

        Clue obj = new Clue(name,description,enigmaId,theme,price);
        try {
            dao.insert(obj);
            NotificationService.getInstance()
                    .notifyObservers("Created new Clue: " + name);
        } catch (Exception e) {
            System.out.println("Error creating Clue: " + e.getMessage());
        }
    }

    public void listClues() {
        System.out.printf("%-4s %-25s %-10s %-20s %-7s %-60s%n",
                "ID", "Name", "Enigma ID", "Theme", "Price", "Description");
        System.out.println("─".repeat(130)); // separator line

        dao.findAll().forEach(o -> {
            String description = o.getDescription();
            if (description.length() > 57) {
                description = description.substring(0, 54) + "...";
            }

            System.out.printf("%-4d %-25s %-10d %-20s %-6.2f€ %-60s%n",
                    o.getId(), o.getName(), o.getEnigmaId(),
                    o.getTheme(), o.getPrice(), description);
        });
        System.out.println("-".repeat(130)); // footer separator
    }

    public void updateClue() {
        listClues();

        int id = InputHandler.readValidId(dao::findById,"Enter Clue ID to update:");
        String name = InputHandler.readOptionalString("Enter a new name (or press Enter to skip): ");
        Integer enigmaId = InputHandler.readValidOrSkipId(enigmaDao::findById,"Enter Enigma new ID (or press 'Enter' to skip): ");
        Theme theme = InputHandler.readOptionalEnum(Theme.class, "Enter a new theme (or press Enter to skip): ");
        BigDecimal price = InputHandler.readOptionalBigDecimal("Enter a new price (or press Enter to skip): ");
        String description = InputHandler.readOptionalString("Enter a new description (or press Enter to skip): ");

        Clue obj = new Clue(id,enigmaId, name,theme,description,price);
        try {
            dao.update(obj);
            NotificationService.getInstance()
                    .notifyObservers("Updated Clue: " + obj.getName());
        } catch (Exception e) {
            System.out.println("Error updating Clue: " + e.getMessage());
        }
    }

    public void deleteClue() {
        listClues();

        int id = InputHandler.readValidId(dao::findById,"Enter Enigma ID to delete: ");
        try {
            Clue clue = dao.findById(id);
            String name = clue.getName();
            dao.delete(clue.getId());
            NotificationService.getInstance()
                    .notifyObservers("Deleted Clue: " + name);
        } catch (Exception e) {
            System.out.println("Error deleting Clue: " + e.getMessage());
        }
    }
}