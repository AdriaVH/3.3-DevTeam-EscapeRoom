package repository.dao;

import db.SQLExecutor;
import enums.Theme;
import model.Enigma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnigmaDAOSQL implements EnigmaDAO {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public void insert(Enigma obj) {
        executor.executeUpdate(
                "INSERT INTO enigma (roomId, name, theme, description) VALUES (?, ?, ?, ?)",
                obj.getRoomId(),
                obj.getName(),
                obj.getTheme().name(),
                obj.getDescription()
        );
    }

    @Override
    public List<Enigma> findAll() {
        List<Enigma> enigmas = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM enigma");

        if (rs == null) {
            System.err.println("⚠️ EnigmaDAOSQL.findAll(): ResultSet is null.");
            return enigmas;
        }

        try {
            while (rs.next()) {
                Enigma enigma = new Enigma();
                enigma.setId(rs.getInt("id"));
                enigma.setRoomId(rs.getInt("roomId"));
                enigma.setName(rs.getString("name"));
                enigma.setTheme(Theme.valueOf(rs.getString("theme")));
                enigma.setDescription("description");
                enigmas.add(enigma);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading enigmas: " + e.getMessage());
        }

        return enigmas;
    }

    @Override
    public void update(Enigma obj) {
        executor.executeUpdate(
                "UPDATE enigma SET roomId = ?, name = ?, theme = ?, description = ? WHERE id = ?",
                obj.getRoomId(),
                obj.getName(),
                obj.getTheme().name(),
                obj.getDescription(),
                obj.getId()
        );
    }

    @Override
    public void delete(int id) {
        executor.executeUpdate("DELETE FROM enigma WHERE id = ?", id);
    }
}