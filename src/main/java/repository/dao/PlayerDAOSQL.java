package repository.dao;

import db.SQLExecutor;
import model.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOSQL {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    public void insert(Player player) {
        String sql = "INSERT INTO player (name, email) VALUES (?, ?)";
        executor.executeUpdate(sql, player.getName(), player.getMail());
    }

    public Player findById(int id) {
        String sql = "SELECT * FROM player WHERE id = ?";
        ResultSet rs = executor.executeQuery(sql, id);
        try {
            if (rs != null && rs.next()) {
                return new Player(rs.getInt("id"), rs.getString("email"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading result in findById: " + e.getMessage());
        }
        return null;
    }

    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player";
        ResultSet rs = executor.executeQuery(sql);
        try {
            while (rs != null && rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("email"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading result in findAll: " + e.getMessage());
        }
        return players;
    }

    public void update(Player player) {
        String sql = "UPDATE player SET name = ?, email = ? WHERE id = ?";
        executor.executeUpdate(sql, player.getName(), player.getMail(), player.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM player WHERE id = ?";
        executor.executeUpdate(sql, id);
    }
}
