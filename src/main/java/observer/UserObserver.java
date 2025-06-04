package observer;

import front.model.User;

public class UserObserver implements Observer {
    private final User user;

    public UserObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(String event, Object data) {
        
    }
}
