package menu;

import manager.PlayerManager;
import model.Player;
import service.PlayerService;
import util.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class PlayerMenu {
    private final PlayerManager playerManager = new PlayerManager();
    private final PlayerService playerService = new PlayerService();

    /*public PlayerAndSalesMenu(Player currentPlayer) {
        this.userManager = new PlayerManager(currentPlayer);
    }*/

    /*public void showPlayers(){
        playerManager.listUsers();
        InputHandler.readInt("Select a player");

    }*/

    public void show() {
        int playerId;
        List<Player> players = new ArrayList<>(playerManager.listUsers());
        System.out.printf("%-4s %-30s%n", "ID", "Email");
        System.out.println("─".repeat(35)); // separator line

        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%-4d %-30s%n", i, players.get(i).getMail());
        }


        playerId = InputHandler.readInt("Select a player"); //canviar el mode de selecció


        int option;
        do {
            System.out.println("\n--- Player & Sales Menu ---");
            System.out.println("1. Assign ScapeRoom ticket");
            System.out.println("2. Sign-up for notifications");
            System.out.println("3. Sign-out for notifications");
            System.out.println("4. See your rewards");
            System.out.println("5. See all assigned tickets");
            System.out.println("0. Back");
            option = InputHandler.readInt("Choose an option: ");

            switch (option) {
                case 1 -> playerManager.buyScapeRoomTicket(players.get(playerId));
                case 2 -> playerManager.signUpUserForNotifications(players.get(playerId));
                case 3 -> playerManager.signOutScapeRoomNotifications(players.get(playerId));
                case 4 -> playerService.getRewardsForPlayer(players.get(playerId));
                case 5 -> System.out.println("userManager.function()");
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}