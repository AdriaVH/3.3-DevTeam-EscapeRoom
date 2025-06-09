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
import java.util.List;
import java.util.stream.Collectors;

public class ClueManager {
    private final ClueDAO dao = new ClueDAOSQL();
    private final EnigmaDAO enigmaDao= new EnigmaDAOSQL();

    public void createClue() {
        String name = InputHandler.readString("Enter a name:");
        Integer enigmaId = readValidEnigmaId("Enter Enigma ID: ");
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

            System.out.printf("%-4d %-25s %-10d %-20s $%-6.2f %-60s%n",
                    o.getId(), o.getName(), o.getEnigmaId(),
                    o.getTheme(), o.getPrice(), description);
        });
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
        Integer enigmaId = readValidEnigmaId("Enter Enigma ID to update: ");
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
    private int readValidEnigmaId(String message) {
        while (true) {
            int id = InputHandler.readInt(message);
            if (enigmaDao.findById(id) != null) {
                return id;
            }
            System.out.println("❌ No Enigma exists with ID: " + id + ". Please try again.");
        }
    }

}