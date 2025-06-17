package menu;

import manager.PlayerManager;
import manager.RewardManager;
import manager.TicketManager;
import repository.dao.PlayerDAO;
import repository.dao.PlayerDAOSQL;
import util.InputHandler;

public class PlayerMenu {
    private final PlayerManager playerManager = new PlayerManager();
    private final RewardManager rewardManager = new RewardManager();
    private final TicketManager ticketManager = new TicketManager();
    private final PlayerDAO dao = new PlayerDAOSQL();

    /*public PlayerAndSalesMenu(Player currentPlayer) {
        this.userManager = new PlayerManager(currentPlayer);
    }*/

    /*public void showPlayers(){
        playerManager.listUsers();
        InputHandler.readInt("Select a player");

    }*/

    public void show() {
        int playerId;
        System.out.printf("%-4s %-30s%n", "ID", "Email");
        System.out.println("─".repeat(35)); // header separator

        playerManager.listUsers().forEach(player ->
                System.out.printf("%-4d %-30s%n", player.getId(), player.getMail())
        );

        System.out.println("-".repeat(35)); // footer separator


        playerId = InputHandler.readValidId(dao::findById,"Select player by ID: "); //canviar el mode de selecció

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
                case 1 -> playerManager.buyScapeRoomTicket(playerId);
                case 2 -> playerManager.signUpUserForNotifications(playerId);
                case 3 -> playerManager.signOutScapeRoomNotifications(playerId);
                case 4 -> rewardManager.listRewards(playerId);
                case 5 -> ticketManager.listTicketsByPlayerId(playerId);
                case 0 -> System.out.println("🔙 Returning...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (option != 0);
    }
}