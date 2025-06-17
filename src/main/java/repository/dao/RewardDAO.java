package repository.dao;

import model.Reward;

import java.util.List;

public interface RewardDAO {
    void insert(Reward reward);
    List<Reward> findAll();
    List<Reward> findByPlayerId(int playerId);
}