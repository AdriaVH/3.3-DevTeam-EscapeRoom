package menu;

import manager.ClueManager;
import util.InputHandler;

public class ClueMenu {
    private final ClueManager clueManager = new ClueManager();

    public void show() {
        int option;
        do {
            System.out.println("\n--- Clue Menu ---");
            System.out.println("1. Create Clue");
            System.out.println("2. List Clues");
            System.out.println("3. Update Clue");
            System.out.println("4. Delete Clue");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> clueManager.createClue();
                case 2 -> clueManager.listClues();
                case 3 -> clueManager.updateClue();
                case 4 -> clueManager.deleteClue();
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}
