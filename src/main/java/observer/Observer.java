package observer;

import model.Player;

public interface Observer {
    public void update(String event);
    public Player getPlayer();
}
