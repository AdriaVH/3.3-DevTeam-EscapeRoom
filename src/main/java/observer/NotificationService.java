package observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Subject{
    //Instancia única Singleton, així tothom farà NotificationSrvice.getInstance(), no cal anar creant instàncies a lo loquissima.
    private static final NotificationService instance = new NotificationService();
    private final List<Observer> observers = new ArrayList<>();
    private NotificationService(){}

    public static NotificationService getInstance(){
        return instance;
    }
    //attach afegeix un observer al llistat
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
    // si has encertat lo del attach imagina que farà detach, vinga crack
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    //The mother of the eggs, aquí cridem a tots els voyeurs observers i els updatem. Seguidament fem una còpia del llistat de la llista
    // amb un "pantallassu" snapshot per evitar Concurrencies per si un observer es desattacha mentre se'està notificant.
    //realment és una flipada que m'ha volgut inmplementar la IA que potser noc al al nostre nivell.
    @Override
    public void notifyObservers(String event, Object data) {
        // Recorrem còpia de la llista per seguretat (en cas que un observer s'elimini durant la iteració)
        List<Observer> snapshot = new ArrayList<>(observers);
        for (Observer obs : snapshot) {
            obs.update(event, data);
        }
    }

    @Override
    public List<Observer> getObservers() {
        return new ArrayList<>(observers);
    }
}
}
