package repository.dao;

import model.Reward;
import model.Ticket;

import java.util.List;

public interface TicketDAO {
    void insert(Ticket ticket);
    List<Ticket> findAll();
    List<Ticket> findByPlayerId(int playerId);
    void delete(int id);
}