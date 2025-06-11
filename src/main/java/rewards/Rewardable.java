package rewards;

import model.Reward;

import java.util.List;

public interface Rewardable {
    void addReward(Reward reward);
    List<Reward> getRewards();
}
