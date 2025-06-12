package menu;

import manager.UserManager;
import model.User;
import util.InputHandler;

public class UserAndSalesMenu {
    private final UserManager userManager;

    public UserAndSalesMenu(User currentUser) {
        this.userManager = new UserManager(currentUser);
    }


    public void show() {
        int option;
        do {
            System.out.println("\n--- User & Sales Menu ---");
            System.out.println("1. Buy ScapeRoom ticket");
            System.out.println("2. Sign-up for notifications");
            System.out.println("3. Sign-out for notifications");
            System.out.println("4. See your achievements and rewards");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> userManager.buyScapeRoomTicket();
                case 2 -> userManager.signUpUserForNotifications();
                case 3 -> userManager.signOutScapeRoomNotifications();
                case 4 -> System.out.println("userManager.function()");
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}