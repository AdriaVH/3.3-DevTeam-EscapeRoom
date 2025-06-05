import model.User;
import observer.NotificationService;
import observer.UserObserver;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Manuelito@gmail.com","Jose");
        User user2 = new User("Morenito_reshulon@hotmail.com", "Eusebio");
        UserObserver uo1 = new UserObserver(user1);
        UserObserver uo2 = new UserObserver(user2);
        NotificationService.getInstance().attach(uo1);
        NotificationService.getInstance().attach(uo2);
        new menu.MainMenu().start();
    }
}
