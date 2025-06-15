import model.Player;
import observer.NotificationService;
import observer.UserObserver;
import repository.dao.PlayerDAOMongo;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Manuelito@gmail.com","Jose");
        Player player2 = new Player("Morenito_reshulon@hotmail.com", "Eusebio");
        Player player = new Player("ignasi@escape.com", "Ignasi");
        Player player3 = new Player("tonyroisterjr@drummer.cat", "Anthony");
        PlayerDAOMongo dao = new PlayerDAOMongo();
        dao.insert(player1);
        dao.insert(player2);
        dao.insert(player3);
        dao.insert(player);
       UserObserver uo1 = new UserObserver(player1);
        UserObserver uo2 = new UserObserver(player2);
        NotificationService.getInstance().attach(uo1);
        NotificationService.getInstance().attach(uo2);

        new menu.MainMenu().start();
    }
}
