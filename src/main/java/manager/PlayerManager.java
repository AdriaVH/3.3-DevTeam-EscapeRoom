package manager;

import model.Player;
import model.ScapeRoom;
import model.Ticket;
import observer.NotificationService;
import observer.UserObserver;
import repository.dao.PlayerDAO;
import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;
import repository.dao.PlayerDAOMongo;
import util.InputHandler;

import java.util.List;

public class PlayerManager {
    //private final UserObserver userObserver = new UserObserver();
    private final PlayerDAOMongo userDAO = new PlayerDAOMongo();

    /*public PlayerManager(Player currentPlayer) {
        this.userObserver = new UserObserver(currentPlayer);
    }*/

    public void buyScapeRoomTicket(int id){
        ScapeRoomManager scapeRoomManager = new ScapeRoomManager();
        System.out.println("Disponible ScapeRoom");
        scapeRoomManager.listScapeRooms();

        int scapeRoomId= InputHandler.readInt("Enter ScaperRoom ID: ");

        ScapeRoomDAO dao= new ScapeRoomDAOSQL();
        ScapeRoom scapeRoom = dao.findById(scapeRoomId);
        PlayerDAO playerDao = new PlayerDAOMongo();
        Player player = playerDao.findById(id);

        if(scapeRoom!=null && player != null){
            Ticket ticket = new Ticket(scapeRoom , player);
            player.addTicket(ticket);
            System.out.println("Player " + player.getName() + " has bought a ticket for ScapeRoom " + scapeRoom.getName() +
                    ". Price :"+scapeRoom.getTicketPrice());
        }
        System.out.println("❌ Unknown ScapeRoom or Player not found.");
    }

        public void signUpUserForNotifications(int id) {
        //PlayerDAOMongo userDAO = new PlayerDAOMongo();
            /*List<Player> players = userDAO.findAll();
            players.forEach(u -> System.out.println(u.getId() + ": " + u.getName()));

            int userId = InputHandler.readInt("🔍 Introdueix l'ID del jugador a subscriure: ");*/
            Player player = userDAO.findById(id);

            if(player != null) {
                NotificationService notificationService = NotificationService.getInstance();
                boolean alreadyAttached = notificationService.getObservers().stream().
                        anyMatch(observer -> observer.getPlayer().getId() == player.getId());

                if(!alreadyAttached) {
                    notificationService.attach(new UserObserver(player));
                    System.out.println(player.getName() + " has been subscribed");
                } else {
                    System.out.println(player.getName() + " is already subscribed");
                }
            }
            /*if (player != null) {
                NotificationService.getInstance().attach(new UserObserver(player));
                System.out.println(player.getName() + " has subscribed");
            } else {
                System.out.println("Player not found.");
            }*/
        }

    public void signOutScapeRoomNotifications(int id){
        /*NotificationService.getInstance().detach(userObserver);
        System.out.println("You unsubscribed from ScapeRoom notifications");*/
        Player player = userDAO.findById(id);

        if(player == null) {
            System.out.println("❌ Player not found.");
            return;
        }

        NotificationService notificationService = NotificationService.getInstance();

        UserObserver observer = notificationService.getObservers().stream().filter(o -> o instanceof UserObserver)
                .map(o -> (UserObserver) o)
                .filter(o -> o.getPlayer().getId() == player.getId())
                .findFirst()
                .orElse(null);

        if(observer != null) {
            notificationService.detach(observer);
            System.out.println(player.getName() + " has been unsubscribed");
        } else {
            System.out.println(player.getName() + " is not subscribed");
        }
    }

    public void listUsers() {
        System.out.printf("%-4s %-30s%n", "ID", "Name");
        System.out.println("─".repeat(35)); // separator line

        userDAO.findAll().forEach(s ->
                System.out.printf("%-4d %-30s%n",
                        s.getId(),
                        s.getName(),
                        s.getMail())
        );
    }
}
