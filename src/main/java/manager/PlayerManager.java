package manager;

import model.Player;
import model.Reward;
import model.ScapeRoom;
import model.Ticket;
import observer.NotificationService;
import observer.UserObserver;
import repository.dao.*;
import util.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private final PlayerDAOSQL playerDAO = new PlayerDAOSQL();

    /*public void buyScapeRoomTicket(int playerId) {
        ScapeRoomManager scapeRoomManager = new ScapeRoomManager();
        System.out.println("Available ScapeRooms");
        scapeRoomManager.listScapeRooms();

        int scapeRoomId = InputHandler.readInt("Enter ScapeRoom ID: ");

        ScapeRoomDAO dao = new ScapeRoomDAOSQL();
        ScapeRoom scapeRoom = dao.findById(scapeRoomId);
        Player player = playerDAO.findById(playerId);

        if (scapeRoom != null && player != null) {
            Ticket ticket = new Ticket(scapeRoom.getId(), player.getId());
            //function with -> IF there is a ticket with same ScapeRoomId and same PlayerId
            //then NO MAKE, and give error


            //BULLSHIT, this should DAO it to the DDBB not locally to the player
            player.addTicket(ticket); //WRONG

            System.out.println(player.getName() + " has bought a ticket for '" + scapeRoom.getName() +
                    "' for: " + scapeRoom.getTicketPrice() + " €");
        }
        System.out.println("❌ Unknown ScapeRoom or Player not found.");
        //MAKE IT AN EXCEPTION AND NOT THIS HORSESHIT

    }*/
    public void buyScapeRoomTicket(int playerId) {
        ScapeRoomManager scapeRoomManager = new ScapeRoomManager();
        ScapeRoomDAO scapeRoomDao = new ScapeRoomDAOSQL();
        Player player = playerDAO.findById(playerId);

        if (player == null) {
            throw new IllegalArgumentException("❌ Player with ID " + playerId + " not found.");
        }

        System.out.println("🎟️ Available ScapeRooms:");
        scapeRoomManager.listScapeRooms();

        int scapeRoomId = InputHandler.readInt("Enter ScapeRoom ID: ");
        ScapeRoom scapeRoom = scapeRoomDao.findById(scapeRoomId);

        if (scapeRoom == null) {
            throw new IllegalArgumentException("❌ ScapeRoom with ID " + scapeRoomId + " not found.");
        }

        TicketDAO ticketDao = new TicketDAOSQL();


        // Create and insert the ticket
        Ticket ticket = new Ticket(scapeRoomId, playerId);
        ticketDao.insert(ticket);

        System.out.println("✅ " + player.getName() + " has bought a ticket for '" +
                scapeRoom.getName() + "' for: " + scapeRoom.getTicketPrice() + " €");
    }

    public void assignRewardToAPlayer(int playerId) {
        RewardDAOSQL rewardDao = new RewardDAOSQL();
        String description = InputHandler.readStringNotNull("Insert the reward description:");
        Reward reward = new Reward(playerId, description);
        rewardDao.insert(reward);
    }


    public void signUpUserForNotifications(int playerId) {
        Player player = playerDAO.findById(playerId);
        if (player != null) {
            NotificationService notificationService = NotificationService.getInstance();
            boolean alreadyAttached = notificationService.getObservers().stream().
                    anyMatch(observer -> observer.getPlayer().getId() == player.getId());

            if (!alreadyAttached) {
                notificationService.attach(new UserObserver(player));
                System.out.println(player.getName() + " has been subscribed");
            } else {
                System.out.println(player.getName() + " is already subscribed");
            }
        }
    }

    public void signOutScapeRoomNotifications(int playerId) {
        Player player = playerDAO.findById(playerId);
        if (player == null) {
            System.out.println("❌ Player not found.");
            return;
        }

        NotificationService notificationService = NotificationService.getInstance();

        UserObserver observer = notificationService.getObservers().stream().filter(o -> o instanceof UserObserver)
                .map(o -> (UserObserver) o)
                .filter(o -> o.getPlayer().getId() == player.getId())
                .findFirst()
                .orElse(null);

        if (observer != null) {
            notificationService.detach(observer);
            System.out.println(player.getName() + " has been unsubscribed");
        } else {
            System.out.println(player.getName() + " is not subscribed");
        }
    }

    public List<Player> listUsers() {
        List<Player> players = new ArrayList<>(playerDAO.findAll());
        return players;
    }
}
