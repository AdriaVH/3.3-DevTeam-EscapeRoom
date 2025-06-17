import model.Player;
import observer.NotificationService;
import observer.UserObserver;
import repository.dao.PlayerDAOSQL;
import service.PlayerService;

public class Main {
    public static void main(String[] args) {
        PlayerService service = new PlayerService();

       /*Service.addPlayer("Benito", "mail@mail.cat");

        PlayerDAOSQL dao = new PlayerDAOSQL();

       UserObserver uo1 = new UserObserver(player1);
        UserObserver uo2 = new UserObserver(player2);
        NotificationService.getInstance().attach(uo1);
        NotificationService.getInstance().attach(uo2);
        */

        new menu.MainMenu().start();
    }
}
