package manager;

import observer.NotificationService;

import enums.Theme;
import model.Clue;
import repository.dao.ClueDAO;
import repository.dao.ClueDAOSQL;
import util.InputHandler;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ClueManager {
    private final ClueDAO dao = new ClueDAOSQL();

    public void createClue() {
        String name = InputHandler.readString("Enter a name:");
        Integer enigmaId = InputHandler.readInt("Enter enigma_id: ");
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
        List<Clue> clues = dao.findAll();
        if (clues.isEmpty()) {
            System.out.println("There are no DecorationItems available to update.");
            return;
        }
        System.out.println("Available DecorationItems:");
        clues.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
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
                    .notifyObservers("Updated Clue: " + obj.getName());
        } catch (Exception e) {
            System.out.println("Error updating Clue: " + e.getMessage());
        }
    }

    public void deleteClue() {
        List<Clue> clues = dao.findAll();
        if (clues.isEmpty()) {
            System.out.println("There are no DecorationItems available to delete.");
            return;
        }
        System.out.println("Available DecorationItems:");
        clues.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter Clue ID to delete: ");
        try {
            dao.delete(id);
            String name = clues.stream()
                    .filter(r -> r.getId() == id)
                    .map(Clue::getName)
                    .collect(Collectors.joining());
            NotificationService.getInstance()
                    .notifyObservers("Deleted Clue: " + name);
        } catch (Exception e) {
            System.out.println("Error deleting Clue: " + e.getMessage());
        }
    }
}