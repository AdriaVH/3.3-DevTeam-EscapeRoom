package manager;
import observer.NotificationService;

import model.ScapeRoom;
import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;
import util.InputHandler;

import java.util.List;
import java.util.stream.Collectors;

public class ScapeRoomManager {
    private final ScapeRoomDAO dao = new ScapeRoomDAOSQL();

    public void createScapeRoom() {
        String name = InputHandler.readString("Enter ScapeRoom name: ");
        ScapeRoom obj = new ScapeRoom(name);
        try {
            dao.insert(obj);
            NotificationService.getInstance()
                    .notifyObservers("Created new EscapeRoom: " + name);
        } catch (Exception e) {
            System.out.println("Error creating EscapeRoom: " + e.getMessage());
        }
    }

    public void listScapeRooms() {
        System.out.printf("%-4s %-30s%n", "ID", "Name");
        System.out.println("─".repeat(35)); // separator line

        dao.findAll().forEach(s ->
                System.out.printf("%-4d %-30s%n",
                        s.getId(),
                        s.getName())
        );
    }


    public void updateScapeRoom() {
        List<ScapeRoom> rooms = dao.findAll();
        if (rooms.isEmpty()) {
            System.out.println("There are no ScapeRooms available to delete.");
            return;
        }
        System.out.println("Available ScapeRooms:");
        rooms.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter ScapeRoom ID to update: ");
        String name = InputHandler.readString("Enter new name: ");
        ScapeRoom obj = new ScapeRoom(name);
        obj.setId(id);
        try {
            dao.update(obj);
            NotificationService.getInstance()
                    .notifyObservers("Updated EscapeRoom: " + name);
        } catch (Exception e) {
            System.out.println("Error updating EscapeRoom: " + e.getMessage());
        }
    }

    /*public void deleteScapeRoom() {
        int id = InputHandler.readInt("Enter ScapeRoom ID to delete: ");
        try {
            dao.delete(id);
            NotificationService.getInstance()
                    .notifyObservers("Deleted EscapeRoom: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting EscapeRoom with id = : " + e.getMessage());
        }
    }*/

    public void deleteScapeRoom() {
        List<ScapeRoom> rooms = dao.findAll();
        if (rooms.isEmpty()) {
            System.out.println("There are no ScapeRooms available to delete.");
            return;
        }
        System.out.println("Available ScapeRooms:");
        rooms.forEach(r -> System.out.println("  " + r.getId() + " → " + r.getName()));
        int id = InputHandler.readInt("Enter ScapeRoom ID to delete: ");
        try {
            boolean exists = rooms.stream().anyMatch(r -> r.getId() == id);
            if (exists) {
                dao.delete(id);
                String name = rooms.stream()
                        .filter(r -> r.getId() == id)
                        .map(ScapeRoom::getName)
                        .collect(Collectors.joining());
                NotificationService.getInstance().notifyObservers("Deleted EscapeRoom: " + name);
            } else {
                System.out.println("No ScapeRoom with ID " + id);
            }
        } catch (RuntimeException e) {
            System.out.println("Error deleting EscapeRoom with id = " + e.getMessage());
        }
    }

}