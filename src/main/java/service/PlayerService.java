package service;

import model.Player;
import model.Reward;
import model.Ticket;
import repository.dao.PlayerDAOSQL;
import repository.dao.RewardDAOSQL;
import repository.dao.TicketDAOSQL;

import java.util.List;

public class PlayerService {
    private final PlayerDAOSQL playerDAO = new PlayerDAOSQL();
    private final RewardDAOSQL rewardDAO = new RewardDAOSQL();
    private final TicketDAOSQL ticketDAO = new TicketDAOSQL();

    // 🔹 Crea un nou jugador
    public void createPlayer(String player) {
        playerDAO.insert(player);
    }

    // 🔹 Afegeix una recompensa a un jugador
    public void addRewardToPlayer(int playerId, String description) {
        Reward reward = new Reward(description, 0, playerId);
        rewardDAO.insert(reward);
    }

    // 🔹 Afegeix un ticket a un jugador
    public void addTicketToPlayer(int playerId, int scapeRoomId) {
        Ticket ticket = new Ticket(0, scapeRoomId, playerId);
        ticketDAO.insert(ticket);
    }

    // 🔹 Llista tots els jugadors
    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    // 🔹 Llista les recompenses d’un jugador
    public List<Reward> getRewardsForPlayer(int playerId) {
        return rewardDAO.findByPlayerId(playerId);
    }

    // 🔹 Llista els tiquets d’un jugador
    public List<Ticket> getTicketsForPlayer(int playerId) {
        return ticketDAO.findByPlayerId(playerId);
    }

    // 🔹 Obté un jugador per ID
    public Player getPlayerById(int id) {
        return playerDAO.findById(id);
    }

    // 🔹 Elimina un jugador
    public void deletePlayer(int id) {
        playerDAO.delete(id);
    }

    // 🔹 Elimina una recompensa
    public void deleteReward(int rewardId) {
        rewardDAO.delete(rewardId);
    }

    // 🔹 Elimina un ticket
    public void deleteTicket(int ticketId) {
        ticketDAO.delete(ticketId);
    }
}
