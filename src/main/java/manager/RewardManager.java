package manager;

import model.Player;
import repository.dao.*;

public class RewardManager {

    private final RewardDAO daoReward = new RewardDAOSQL();
    public void listRewards(int playerId) {
        PlayerDAO daoPlayer = new PlayerDAOSQL();
        Player player = daoPlayer.findById(playerId);
        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.println("Rewards for player: " + player.getName());
        System.out.printf("%-4s %-50s%n", "ID", "Reward");
        System.out.println("─".repeat(60)); // Adjusted separator

        daoReward.findByPlayerId(playerId).forEach(reward ->
                System.out.printf("%-4d %-50s%n",
                        reward.getId(),
                        reward.getDescription() != null ? reward.getDescription() : "(No description)")
        );
        System.out.println("-".repeat(60));
    }

}
