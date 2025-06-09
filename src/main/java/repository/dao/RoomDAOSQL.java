package repository.dao;

import db.SQLExecutor;
import enums.Theme;
import model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOSQL implements RoomDAO {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public void insert(Room room) {
        String query = "INSERT INTO room (scape_room_id, difficult_level, theme) VALUES (?, ?, ?)";
        executor.executeUpdate(query,
                room.getScapeRoomId(),
                room.getDifficultLevel().name(),
                room.getTheme().name());
    }
@Override
    public Room findById(int id) {
        ResultSet rs = executor.executeQuery("SELECT * FROM room WHERE id = ?", id);

        try {
            if (rs != null && rs.next()) {
                return new Room(
                        rs.getInt("id"),
                        rs.getInt("scape_room_id"), // adjust if your DB column is different
                        Room.DifficultLevel.valueOf(rs.getString("difficult_level")),
                        Theme.valueOf(rs.getString("theme"))
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error finding Room by ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM room");

        if (rs == null) {
            System.err.println("⚠️ RoomDAOSQL.findAll(): ResultSet is null.");
            return rooms;
        }

        try {
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("scape_room_id"),
                        Room.DifficultLevel.valueOf(rs.getString("difficult_level")),
                        Theme.valueOf(rs.getString("theme")));
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading rooms: " + e.getMessage());
        }

        return rooms;
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE room SET scape_room_id = ?, difficult_level = ?, theme = ? WHERE id = ?";
        executor.executeUpdate(query, room.getScapeRoomId(), room.getDifficultLevel().name(), room.getTheme().name(), room.getId());
    }

    @Override
    public void delete(int id) {
        executor.executeUpdate("DELETE FROM room WHERE id = ?", id);
    }
}