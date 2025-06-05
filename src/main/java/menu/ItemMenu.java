package menu;

import util.InputHandler;

public class ItemMenu {
    private final EnigmaMenu enigmaMenu = new EnigmaMenu();
    private final DecorationItemMenu decorationItemMenu = new DecorationItemMenu();
    private final ClueMenu clueMenu = new ClueMenu();

    public void show() {
        int option;
        do {
            System.out.println("\n-- Item Menu --");
            System.out.println("1. Manage Enigmas");
            System.out.println("2. Manage Decoration Items");
            System.out.println("3. Manage Clues");
            System.out.println("0. Back to Main Menu");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> enigmaMenu.show();
                case 2 -> decorationItemMenu.show();
                case 3 -> clueMenu.show();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }
}