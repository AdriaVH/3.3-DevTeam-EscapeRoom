package menu;

import manager.DecorationItemManager;
import util.InputHandler;

public class DecorationItemMenu {
    private final DecorationItemManager decorationItemManager = new DecorationItemManager();

    public void show() {
        int option;
        do {
            System.out.println("\n--- Decoration Item Menu ---");
            System.out.println("1. Create Decoration Item");
            System.out.println("2. List Decoration Items");
            System.out.println("3. Update Decoration Item");
            System.out.println("4. Delete Decoration Item");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> decorationItemManager.createDecorationItem();
                case 2 -> decorationItemManager.listDecorationItems();
                case 3 -> decorationItemManager.updateDecorationItem();
                case 4 -> decorationItemManager.deleteDecorationItem();
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}
