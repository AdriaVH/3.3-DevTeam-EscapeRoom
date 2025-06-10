package menu;

import util.InputHandler;

public class UserAndSalesMenu {
    //private final UserManager userManager = new Usermanager();

    public void show() {
        int option;
        do {
            System.out.println("\n--- User & Sales Menu ---");
            System.out.println("1. Buy ScapeRoom ticket");
            System.out.println("2. Sign-up for notifications");
            System.out.println("3. Sign-out for notifications");
            System.out.println("4. See your achivements and rewards");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> System.out.println("userManager.function()");
                case 2 -> System.out.println("userManager.function()");
                case 3 -> System.out.println("userManager.function()");
                case 4 -> System.out.println("userManager.function()");
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}
