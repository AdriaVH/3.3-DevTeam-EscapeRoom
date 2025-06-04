package menus;

import front.dao.*;
import front.model.*;

import java.util.List;
import java.util.Observable;
import java.util.Scanner;

/**
 * Menú principal per gestionar les diferents entitats i les seves operacions.
 */
public class MenuSQL implements Observable {
    private final Scanner sc = new Scanner(System.in);
    private final ScapeRoomDAO scapeRoomDAO = new ScapeRoomDAO();
    private final RoomDAO roomDAO = new RoomDAO();
    private final DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
    private final EnigmaDAO enigmaDAO = new EnigmaDAO();
    private final ClueDAO clueDAO = new ClueDAO();

    public void start() {
        List<MenuOption> mainOptions = List.of(
            new MenuOption("Exit", () -> System.out.println("👋 Exiting...")),
            new MenuOption("Manage ScapeRooms", this::scapeRoomMenu),
            new MenuOption("Manage Rooms", this::roomMenu),
            new MenuOption("Manage Items", this::itemMenu)
        );
        new ConsoleMenu("Main Menu", mainOptions, sc).display();
    }

    private void scapeRoomMenu() {
        List<MenuOption> options = List.of(
            new MenuOption("Back", () -> {}),
            new MenuOption("Show all", () -> {
                List<ScapeRoom> list = scapeRoomDAO.getAll();
                list.forEach(System.out::println);
            }),
            new MenuOption("Create new", () -> {
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                scapeRoomDAO.create(new ScapeRoom(name));
            }),
            new MenuOption("Update existing", () -> {
                List<ScapeRoom> list = scapeRoomDAO.getAll();
                list.forEach(System.out::println);
                System.out.print("Enter ID to update: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                scapeRoomDAO.update(new ScapeRoom(id, name));
            }),
            new MenuOption("Delete", () -> {
                System.out.print("Enter ID to delete: ");
                int id = Integer.parseInt(sc.nextLine());
                scapeRoomDAO.delete(id);
            })
        );  
        new ConsoleMenu("ScapeRoom Menu", options, sc).display();
    }

    private void roomMenu() {
        List<MenuOption> options = List.of(
            new MenuOption("Back", () -> {}),
            new MenuOption("Show all", () -> {
                List<Room> list = roomDAO.getAll();
                list.forEach(System.out::println);
            }),
            new MenuOption("Create new", () -> {
                System.out.print("Enter scape_room_id: ");
                int srId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter difficult_level: ");
                String dl = sc.nextLine();
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                roomDAO.create(new Room(srId, dl, theme));
            }),
            new MenuOption("Update existing", () -> {
                List<Room> list = roomDAO.getAll();
                list.forEach(System.out::println);
                System.out.print("Enter ID to update: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Enter scape_room_id: ");
                int srId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter difficult_level: ");
                String dl = sc.nextLine();
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                roomDAO.update(new Room(id, srId, dl, theme));
            }),
            new MenuOption("Delete", () -> {
                System.out.print("Enter ID to delete: ");
                int id = Integer.parseInt(sc.nextLine());
                roomDAO.delete(id);
            })
        );
        new ConsoleMenu("Room Menu", options, sc).display();
    }

    private void itemMenu() {
        List<MenuOption> options = List.of(
            new MenuOption("Back", () -> {}),
            new MenuOption("Manage DecorationItems", this::decorationItemMenu),
            new MenuOption("Manage Enigmas", this::enigmaMenu),
            new MenuOption("Manage Clues", this::clueMenu)
        );
        new ConsoleMenu("Item Menu", options, sc).display();
    }

    private void decorationItemMenu() {
        List<MenuOption> options = List.of(
            new MenuOption("Back", () -> {}),
            new MenuOption("Show all", () -> {
                front.dao.DecorationItemDAO dao = decorationItemDAO;
                dao.getAll().forEach(System.out::println);
            }),
            new MenuOption("Create new", () -> {
                System.out.print("Enter item_id: ");
                int itemId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter material: ");
                String material = sc.nextLine();
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                System.out.print("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                decorationItemDAO.create(new front.model.DecorationItem(itemId, material, theme, price));
            }),
            new MenuOption("Update existing", () -> {
                decorationItemDAO.getAll().forEach(System.out::println);
                System.out.print("Enter ID to update: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Enter item_id: ");
                int itemId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter material: ");
                String material = sc.nextLine();
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                System.out.print("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                decorationItemDAO.update(new front.model.DecorationItem(id, itemId, material, theme, price));
            }),
            new MenuOption("Delete", () -> {
                System.out.print("Enter ID to delete: ");
                int id = Integer.parseInt(sc.nextLine());
                decorationItemDAO.delete(id);
            })
        );
        new ConsoleMenu("DecorationItem Menu", options, sc).display();
    }

    private void enigmaMenu() {
        List<MenuOption> options = List.of(
            new MenuOption("Back", () -> {}),
            new MenuOption("Show all", () -> {
                enigmaDAO.getAll().forEach(System.out::println);
            }),
            new MenuOption("Create new", () -> {
                System.out.print("Enter item_id: ");
                int itemId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                enigmaDAO.create(new front.model.Enigma(itemId, theme));
            }),
            new MenuOption("Update existing", () -> {
                enigmaDAO.getAll().forEach(System.out::println);
                System.out.print("Enter ID to update: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Enter item_id: ");
                int itemId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                enigmaDAO.update(new front.model.Enigma(id, itemId, theme));
            }),
            new MenuOption("Delete", () -> {
                System.out.print("Enter ID to delete: ");
                int id = Integer.parseInt(sc.nextLine());
                enigmaDAO.delete(id);
            })
        );
        new ConsoleMenu("Enigma Menu", options, sc).display();
    }

    private void clueMenu() {
        List<MenuOption> options = List.of(
            new MenuOption("Back", () -> {}),
            new MenuOption("Show all", () -> {
                clueDAO.getAll().forEach(System.out::println);
            }),
            new MenuOption("Create new", () -> {
                System.out.print("Enter enigma_id: ");
                int enigmaId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter item_id: ");
                int itemId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                System.out.print("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                clueDAO.create(new front.model.Clue(enigmaId, itemId, theme, price));
            }),
            new MenuOption("Update existing", () -> {
                clueDAO.getAll().forEach(System.out::println);
                System.out.print("Enter ID to update: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Enter enigma_id: ");
                int enigmaId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter item_id: ");
                int itemId = Integer.parseInt(sc.nextLine());
                System.out.print("Enter theme: ");
                String theme = sc.nextLine();
                System.out.print("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                clueDAO.update(new front.model.Clue(id, enigmaId, itemId, theme, price));
            }),
            new MenuOption("Delete", () -> {
                System.out.print("Enter ID to delete: ");
                int id = Integer.parseInt(sc.nextLine());
                clueDAO.delete(id);
            })
        );
        new ConsoleMenu("Clue Menu", options, sc).display();
    }
}
