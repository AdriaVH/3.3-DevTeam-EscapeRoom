package observer;

import model.Player;

public interface Observer {
     void update(String event);
     Player getPlayer();
}
