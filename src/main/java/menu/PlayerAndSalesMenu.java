package menu;

import manager.PlayerManager;
import util.InputHandler;

public class PlayerAndSalesMenu {
    private final PlayerManager playerManager = new PlayerManager();

    /*public PlayerAndSalesMenu(Player currentPlayer) {
        this.userManager = new PlayerManager(currentPlayer);
    }*/

    public void showPlayers(){
        playerManager.listUsers();
        InputHandler.readInt("Select a player");

    }

    public void show() {
        int player;
        playerManager.listUsers();
        player = InputHandler.readInt("Select a player");

        int option;
        do {
            System.out.println("\n--- Player & Sales Menu ---");
            System.out.println("1. Buy ScapeRoom ticket");
            System.out.println("2. Sign-up for notifications");
            System.out.println("3. Sign-out for notifications");
            System.out.println("4. See your achievements and rewards");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> playerManager.buyScapeRoomTicket(player);
                case 2 -> playerManager.signUpUserForNotifications(player);
                case 3 -> playerManager.signOutScapeRoomNotifications(player);
                case 4 -> System.out.println("userManager.function()");
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}