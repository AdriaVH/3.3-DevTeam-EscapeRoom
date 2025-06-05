package menu;

import manager.EnigmaManager;
import util.InputHandler;

public class EnigmaMenu {
    private final EnigmaManager enigmaManager = new EnigmaManager();

    public void show() {
        int option;
        do {
            System.out.println("\n--- Enigma Menu ---");
            System.out.println("1. Create Enigma");
            System.out.println("2. List Enigmas");
            System.out.println("3. Update Enigma");
            System.out.println("4. Delete Enigma");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> enigmaManager.createEnigma();
                case 2 -> enigmaManager.listEnigmas();
                case 3 -> enigmaManager.updateEnigma();
                case 4 -> enigmaManager.deleteEnigma();
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}
