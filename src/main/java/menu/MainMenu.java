package menu;

import util.InputHandler;

public class MainMenu {
    private final ScapeRoomMenu scapeRoomMenu = new ScapeRoomMenu();
    private final RoomMenu roomMenu = new RoomMenu();
    private final ItemMenu itemMenu = new ItemMenu();
    private final PlayerMenu userMenu = new PlayerMenu();


    public void start() {
        int option;
        do {
            System.out.println("\n=== Escape Room Manager ===");
            System.out.println("1. Manage ScapeRooms");
            System.out.println("2. Manage Rooms");
            System.out.println("3. Manage Items");
            System.out.println("4. Manage Players");

            System.out.println("0. Exit");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> scapeRoomMenu.show();
                case 2 -> roomMenu.show();
                case 3 -> itemMenu.show();
                case 4 -> userMenu.show();
                case 0 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}
