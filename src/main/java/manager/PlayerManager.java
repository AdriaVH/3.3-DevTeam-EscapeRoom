package manager;

import model.Player;
import model.ScapeRoom;
import observer.NotificationService;
import observer.UserObserver;
import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;
import repository.dao.PlayerDAOMongo;
import util.InputHandler;

import java.util.List;

public class PlayerManager {
    private final UserObserver userObserver;

    public PlayerManager(Player currentPlayer) {
        this.userObserver = new UserObserver(currentPlayer);
    }

    public void buyScapeRoomTicket(){
        ScapeRoomManager scapeRoomManager = new ScapeRoomManager();
        System.out.println("Disponible ScapeRoom");
        scapeRoomManager.listScapeRooms();
        int scapeRoomId= InputHandler.readInt("Enter ScaperRoom ID: ");
        ScapeRoomDAO dao= new ScapeRoomDAOSQL();
        ScapeRoom scapeRoom = dao.findById(scapeRoomId);
        if(scapeRoom!=null){
            System.out.println("You bought a ticket for ScapeRoom "+scapeRoom.getName()+ ". Price :"+scapeRoom.getTicketPrice());
        }
        System.out.println("Unknown ScapeRoom");
    }

        public void signUpUserForNotifications() {
        PlayerDAOMongo userDAO = new PlayerDAOMongo();
            List<Player> players = userDAO.findAll();
            players.forEach(u -> System.out.println(u.getId() + ": " + u.getName()));

            int userId = InputHandler.readInt("🔍 Introdueix l'ID del jugador a subscriure: ");
            Player player = userDAO.findById(userId);

            if (player != null) {
                NotificationService.getInstance().attach(new UserObserver(player));
                System.out.println(player.getName() + " has subscribed");
            } else {
                System.out.println("Player not found.");
            }
        }

    public void signOutScapeRoomNotifications(){
        NotificationService.getInstance().detach(userObserver);
        System.out.println("You unsubscribed from ScapeRoom notifications");


    }


}
