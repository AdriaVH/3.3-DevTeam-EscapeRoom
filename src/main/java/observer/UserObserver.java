package observer;

import model.Player;

public class UserObserver implements Observer {
    private final Player player;

    public UserObserver(Player player) {
        this.player = player;
    }

    @Override
    public void update(String event) {
        player.addNotification(event);
        System.out.printf(">>> Notification for %s (%s): %s%n",
                          player.getName(), player.getMail(), event);
    }
}
