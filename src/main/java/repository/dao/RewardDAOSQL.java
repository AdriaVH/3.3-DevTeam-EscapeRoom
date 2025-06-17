package repository.dao;

import db.SQLExecutor;
import model.Reward;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RewardDAOSQL implements RewardDAO {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    public void insert(Reward reward) {
        String sql = "INSERT INTO reward (player_id, description) VALUES (?, ?)";
        executor.executeUpdate(sql, reward.getPlayerId(), reward.getDescription());
    }

    public List<Reward> findByPlayerId(int playerId) {
        List<Reward> rewards = new ArrayList<>();
        String sql = "SELECT * FROM reward WHERE player_id = ?";
        ResultSet rs = executor.executeQuery(sql, playerId);

        try {
            while (rs != null && rs.next()) {
                Reward reward = new Reward(
                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("description"));
                rewards.add(reward);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading rewards: " + e.getMessage());
        }

        return rewards;
    }

    public void delete(int id) {
        String sql = "DELETE FROM reward WHERE id = ?";
        executor.executeUpdate(sql, id);
    }

    public List<Reward> findAll() {
        List<Reward> rewards = new ArrayList<>();
        String sql = "SELECT * FROM reward";
        ResultSet rs = executor.executeQuery(sql);

        try {
            while (rs != null && rs.next()) {
                Reward reward = new Reward(

                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("description"));
                rewards.add(reward);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading all rewards: " + e.getMessage());
        }
        return rewards;
    }

}
