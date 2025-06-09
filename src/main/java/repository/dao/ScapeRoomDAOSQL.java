package repository.dao;

import db.SQLExecutor;
import model.ScapeRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScapeRoomDAOSQL implements ScapeRoomDAO {
    private static final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public void insert(ScapeRoom obj) {
        executor.executeUpdate("INSERT INTO scaperoom (name) VALUES (?)", obj.getName());
    }
    @Override
    public ScapeRoom findById(int id) {
        ResultSet rs = executor.executeQuery("SELECT * FROM scaperoom WHERE id = ?", id);

        try {
            if (rs != null && rs.next()) {
                return new ScapeRoom(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error finding ScapeRoom by ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<ScapeRoom> findAll() {
        List<ScapeRoom> scapeRooms = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM scaperoom");

        if (rs == null) {
            System.err.println("⚠️ ScapeRoomDAOSQL.findAll(): ResultSet is null.");
            return scapeRooms;
        }

        try {
            while (rs.next()) {
                ScapeRoom scapeRoom = new ScapeRoom(
                        rs.getInt("id"),
                        rs.getString("name"));
                scapeRooms.add(scapeRoom);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading scaperooms: " + e.getMessage());
        }

        return scapeRooms;
    }

    @Override
    public void update(ScapeRoom obj) {
        executor.executeUpdate("UPDATE scaperoom SET name = ? WHERE id = ?", obj.getName(), obj.getId());
    }

    @Override
    public void delete(int id) {
        int rowsAffected = executor.executeUpdate("DELETE FROM scaperoom WHERE id = ?", id);
        if (rowsAffected == 0) {
            throw new RuntimeException(" " + id);
        }
    }
}