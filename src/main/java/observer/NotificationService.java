package observer;


import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Subject {
    private static final NotificationService instance = new NotificationService();
    private final List<Observer> observers = new ArrayList<>();

    private NotificationService() {
    }

    public static NotificationService getInstance() {
        return instance;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        List<Observer> snapshot = new ArrayList<>(observers);
        for (Observer obs : snapshot) {
            obs.update(event);
        }
    }

    @Override
    public List<Observer> getObservers() {
        return new ArrayList<>(observers);
    }
}

