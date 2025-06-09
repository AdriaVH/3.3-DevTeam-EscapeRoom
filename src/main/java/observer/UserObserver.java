package observer;

import model.User;

public class UserObserver implements Observer {
    private final User user;

    public UserObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(String event) {
        user.addNotification(event);
        System.out.printf(">>> Notification for %s (%s): %s%n",
                          user.getName(), user.getMail(), event);
    }
}
