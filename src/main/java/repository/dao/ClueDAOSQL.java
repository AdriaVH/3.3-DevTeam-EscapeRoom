package repository.dao;

import db.SQLExecutor;
import enums.Theme;
import model.Clue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClueDAOSQL implements ClueDAO {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public void insert(Clue obj) {
        executor.executeUpdate(
                "INSERT INTO clue (name, enigma_id, theme, description, price) VALUES (?, ?, ?, ?, ?)",
                obj.getName(),
                obj.getEnigmaId(),
                obj.getTheme().name(),
                obj.getDescription(),
                obj.getPrice()
        );
    }

    @Override
    public List<Clue> findAll() {
        List<Clue> clues = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM clue");

        if (rs == null) {
            System.err.println("⚠️ ClueDAOSQL.findAll(): ResultSet is null.");
            return clues;
        }

        try {
            while (rs.next()) {
                Clue clue = new Clue(
                rs.getInt("id"),
                rs.getInt("enigma_id"),
                rs.getString("name"),
                Theme.valueOf(rs.getString("theme")),
                rs.getString("description"),
                rs.getBigDecimal("price"));
                clues.add(clue);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading clues: " + e.getMessage());
        }
        return clues;
    }

    @Override
    public void update(Clue obj) {
        String query = "UPDATE clue SET enigma_id = CASE WHEN ? = 0 THEN NULL ELSE COALESCE(?, enigma_id) END, " +
                "name = COALESCE(?, name), " +
                "theme = COALESCE(?, theme), " +
                "description = COALESCE(?, description), " +
                "price = COALESCE(?, price) " +
                "WHERE id = ?";

        executor.executeUpdate(query,
                obj.getEnigmaId(), obj.getEnigmaId(),
                obj.getName(),
                obj.getTheme(),
                obj.getDescription(),
                obj.getPrice(),
                obj.getId());
    }



    @Override
    public void delete(int id) {
        executor.executeUpdate("DELETE FROM clue WHERE id = ?", id);
    }

    @Override
    public Clue findById(int id) {
        ResultSet rs = executor.executeQuery("SELECT * FROM clue WHERE id = ?", id);
        try {
            if (rs != null && rs.next()) {
                return new Clue(
                        rs.getInt("id"),
                        rs.getInt("enigma_id"),
                        rs.getString("name"),
                        Theme.valueOf(rs.getString("theme")),
                        rs.getString("description"),
                        rs.getBigDecimal("price")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error finding Clue by ID: " + e.getMessage());
        }
        return null;
    }

}