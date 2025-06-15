package service;

import model.Player;
import model.Reward;
import model.Ticket;
import repository.dao.PlayerDAOMongo;
import repository.dao.RewardDAOMongo;
import repository.dao.TicketDAOMongo;

import java.util.List;

public class PlayerService {

    private final PlayerDAOMongo playerDAO = new PlayerDAOMongo();
    private final RewardDAOMongo rewardDAO = new RewardDAOMongo();
    private final TicketDAOMongo ticketDAO = new TicketDAOMongo();

    public void addPlayer(String name, String mail) {
        Player player = new Player(mail, name);
        playerDAO.insert(player);
        System.out.println("✅ Player added: " + name);
    }

    public void deletePlayerByMail(String mail) {
        playerDAO.deleteByMail(mail);
        System.out.println("🗑️ Player deleted: " + mail);
    }

    public Player findByMail(String mail) {
        return playerDAO.findByMail(mail);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public void addRewardToPlayer(String mail, Reward reward) {
        reward.setPlayerMail(mail);
        rewardDAO.insert(reward);
        System.out.println("🎁 Reward added to: " + mail);
    }

    public void addTicketToPlayer(String mail, Ticket ticket) {
        ticket.setPlayerMail(mail);
        ticketDAO.insert(ticket);
        System.out.println("🎫 Ticket added to: " + mail);
    }

    public List<Reward> getRewardsForPlayer(String mail) {
        return rewardDAO.findByPlayerMail(mail);
    }

    public List<Ticket> getTicketsForPlayer(String mail) {
        return ticketDAO.findByPlayerMail(mail);
    }
}
