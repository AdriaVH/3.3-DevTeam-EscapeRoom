package manager;
import observer.NotificationService;

import enums.Theme;
import model.Clue;
import repository.dao.ClueDAO;
import repository.dao.ClueDAOSQL;
import util.InputHandler;

import java.math.BigDecimal;

public class ClueManager {
    private final ClueDAO dao = new ClueDAOSQL();

    public void createClue() {
        String name = InputHandler.readString("Enter a name:");
        Integer enigmaId = InputHandler.readOptionalInt("Enter enigma_id (or press Enter to skip): ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter theme");
        String description = InputHandler.readString("Enter description");
        BigDecimal price = InputHandler.readBigDecimal("Enter price: ");

        Clue obj = new Clue(name,description,enigmaId,theme,price);
        try {
            dao.insert(obj);
            NotificationService.getInstance()
                    .notifyObservers("Created new Clue: " + obj.getId());
        } catch (Exception e) {
            System.out.println("Error creating Clue: " + e.getMessage());
        }
    }

    public void listClues() {
        dao.findAll().forEach(o ->
                System.out.println("ID: " + o.getId() +
                        " | name: " + o.getName() +
                        " | enigma_id: " + o.getEnigmaId() +
                        " | theme: " + o.getTheme() +
                        " | description: " +o.getDescription()+
                        " | price: " + o.getPrice())
        );
    }

    public void updateClue() {
        int id = InputHandler.readInt("Enter Clue ID to update:");
        String name = InputHandler.readString("Enter a new name:");
        Integer enigmaId = InputHandler.readOptionalInt("Enter a new enigma_id (or press Enter to skip): ");
        Theme theme = InputHandler.readEnum(Theme.class, "Enter a new theme");
        BigDecimal price = InputHandler.readBigDecimal("Enter a new price: ");
        String description = InputHandler.readString("Enter a new description");

        Clue obj = new Clue(name,description,enigmaId,theme,price);
        obj.setId(id);
        try {
            dao.update(obj);
            NotificationService.getInstance()
                    .notifyObservers("Updated Clue: " + obj.getId());
        } catch (Exception e) {
            System.out.println("Error updating Clue: " + e.getMessage());
        }
    }

    public void deleteClue() {
        int id = InputHandler.readInt("Enter Clue ID to delete: ");
        try {
            dao.delete(id);
            NotificationService.getInstance()
                    .notifyObservers("Deleted Clue: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting Clue: " + e.getMessage());
        }
    }
}