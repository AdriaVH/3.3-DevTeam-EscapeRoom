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
        executor.executeUpdate("INSERT INTO scaperoom (name, ticket_price) VALUES (?,?)", obj.getName(), obj.getTicketPrice());
    }

    @Override
    public ScapeRoom findById(int id) {
        ResultSet rs = executor.executeQuery("SELECT * FROM scaperoom WHERE id = ?", id);

        try {
            if (rs != null && rs.next()) {
                return new ScapeRoom(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("ticket_price")
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
                ScapeRoom scRoom = new ScapeRoom(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("ticket_price"));
                scapeRooms.add(scRoom);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading scaperooms: " + e.getMessage());
        }
        return scapeRooms;
    }

    @Override
    public void update(ScapeRoom obj) {
        executor.executeUpdate(
                "UPDATE scaperoom " +
                        "SET name = COALESCE(?, name), " +
                        "ticket_price = COALESCE(?, ticket_price) " +
                        "WHERE id = ?",
                obj.getName(),
                obj.getTicketPrice(),
                obj.getId()
        );
    }

    public double totalPrice (int id) {
        double totalPrice = 0;
        try {
            ResultSet rs = executor.executeQuery(
                "SELECT SUM(price) AS total_price " +
                        "FROM (" +
                        "SELECT c.price FROM clue c " +
                        "JOIN enigma e ON c.enigma_id = e.id " +
                        "JOIN room r ON e.room_id = r.id " +
                        "WHERE r.scape_room_id = ? " +
                        "UNION ALL " +
                        "SELECT d.price FROM decorationitem d " +
                        "JOIN room r ON d.room_id = r.id " +
                        "WHERE r.scape_room_id = ?" +
                        ") AS combined",
                id, // for clue
                id  // for decorationitem
        );

        if (rs.next()) {
            totalPrice = rs.getDouble("total_price");
        }

        } catch (SQLException e) {
            System.err.println("❌ Error reading scaperooms: " + e.getMessage());
        }
        return totalPrice;
    }


    @Override
    public void delete(int id) {
        int rowsAffected = executor.executeUpdate("DELETE FROM scaperoom WHERE id = ?", id);
        if (rowsAffected == 0) {
            throw new RuntimeException(" " + id);
        }
    }
}