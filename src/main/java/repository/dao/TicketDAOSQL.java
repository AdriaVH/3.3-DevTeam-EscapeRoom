package repository.dao;

import db.SQLExecutor;
import model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOSQL implements TicketDAO {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    public void insert(Ticket ticket) {
        String sql = "INSERT INTO ticket (player_id, scape_room_id) VALUES (?, ?)";
        executor.executeUpdate(sql, ticket.getPlayerId(), ticket.getScapeRoomId());
    }

    public List<Ticket> findByPlayerId(int playerId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket WHERE player_id = ?";
        ResultSet rs = executor.executeQuery(sql, playerId);

        try {
            while (rs != null && rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("scape_room_id"),
                        rs.getInt("player_id")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading tickets: " + e.getMessage());
        }

        return tickets;
    }

    public void delete(int id) {
        String sql = "DELETE FROM ticket WHERE id = ?";
        executor.executeUpdate(sql, id);
    }

    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        ResultSet rs = executor.executeQuery(sql);

        try {
            while (rs != null && rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("scape_room_id"),
                        rs.getInt("player_id")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading all tickets: " + e.getMessage());
        }
        return tickets;
    }
}
