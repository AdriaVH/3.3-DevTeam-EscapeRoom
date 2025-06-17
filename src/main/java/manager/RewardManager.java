package manager;

import exceptions.UnpaidTicketException;
import model.Reward;
import model.ScapeRoom;
import model.Player;
import repository.dao.*;

public class RewardManager {

    private final RewardDAO daoReward = new RewardDAOSQL();

    public static void checkRewards(Player player, boolean hasPaidTicket, ScapeRoom scapeRoom) throws UnpaidTicketException {
        if (!hasPaidTicket) {
            throw new UnpaidTicketException(player.getName());
        }

        Reward reward = new Reward(player.getId(), "Your reward for ScapeRoom completion");
        //player.addReward(reward);
        System.out.println("🎉 Reward to " + player.getName() + ": " + reward);
    }
    public void getRewardsForPlayer(int playerId) {

    }
    public void listRewards(int playerId) {
        PlayerDAO daoPlayer = new PlayerDAOSQL();
        Player player = daoPlayer.findById(playerId);
        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        // Header with player name
        System.out.println("Rewards for player: " + player.getName());
        System.out.printf("%-4s %-50s%n", "ID", "Description");
        System.out.println("─".repeat(60)); // Adjusted separator

        // List rewards
        daoReward.findByPlayerId(playerId).forEach(reward ->
                System.out.printf("%-4d %-50s%n",
                        reward.getId(),
                        reward.getDescription() != null ? reward.getDescription() : "(No description)")
        );
    }

}
