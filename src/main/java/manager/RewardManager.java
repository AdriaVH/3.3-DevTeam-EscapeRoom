package manager;

import exceptions.UnpaidTicketException;
import model.Reward;
import model.ScapeRoom;
import model.Player;

public class RewardManager {
    public static void checkRewards(Player player, boolean hasPaidTicket, ScapeRoom scapeRoom) throws UnpaidTicketException {
        if (!hasPaidTicket) {
            throw new UnpaidTicketException(player.getName());
        }

        Reward reward = new Reward("You passed the ScapeRoom " + scapeRoom.getName(), 50);
        player.addReward(reward);
        System.out.println("🎉 Reward to " + player.getName() + ": " + reward);
    }
}
