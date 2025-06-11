package manager;

import model.ScapeRoom;
import model.User;
import observer.NotificationService;
import observer.Observer;
import observer.UserObserver;
import repository.dao.ScapeRoomDAO;
import repository.dao.ScapeRoomDAOSQL;
import repository.dao.UserDAOSQL;
import util.InputHandler;

import java.util.List;

public class UserManager {
    private final UserObserver userObserver;

    public UserManager(User currentUser) {
        this.userObserver = new UserObserver(currentUser);
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
        UserDAOSQL userDAO = new UserDAOSQL();
            List<User> users = userDAO.findAll();
            users.forEach(u -> System.out.println(u.getId() + ": " + u.getName()));

            int userId = InputHandler.readInt("🔍 Introdueix l'ID del jugador a subscriure: ");
            User user = userDAO.findById(userId);

            if (user != null) {
                NotificationService.getInstance().attach(new UserObserver(user));
                System.out.println(user.getName() + " has subscribed");
            } else {
                System.out.println("User not found.");
            }
        }

    public void signOutScapeRoomNotifications(){
        NotificationService.getInstance().detach(userObserver);
        System.out.println("You unsubscribed from ScapeRoom notifications");


    }


}
