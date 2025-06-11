package manager;

import model.Reward;
import model.ScapeRoom;
import model.User;

public class RewardManager {
    public static void checkRewards(User user, boolean hasPaidTicket, ScapeRoom scapeRoom) {
        if(hasPaidTicket){
            Reward reward= new Reward("You passed the ScapeRoom "+ scapeRoom.getName() , 50);
            user.addReward(reward);
            System.out.println("Reward to "+user.getName()+":"+ reward);

        }
        System.out.println("⛔ " + user.getName() + " didn't pay the ticket");
    }
}
