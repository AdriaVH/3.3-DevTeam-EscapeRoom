package menu;

import manager.RoomManager;
import util.InputHandler;

public class RoomMenu {
    private final RoomManager manager = new RoomManager();

    public void show() {
        int option;
        do {
            System.out.println("\n--- Room Menu ---");
            System.out.println("1. Create Room");
            System.out.println("2. List Rooms");
            System.out.println("3. Update Room");
            System.out.println("4. Delete Room");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> manager.createRoom();
                case 2 -> manager.listRooms();
                case 3 -> manager.updateRoom();
                case 4 -> manager.deleteRoom();
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}
