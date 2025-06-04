package dao;

import ConnectionDB.SQLExecutor;
import front.model.ScapeRoom;
import front.observer.NotificationService;
import front.observer.Observer;
import front.observer.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScapeRoomDAO implements Subject {
    private final SQLExecutor executor = new SQLExecutor();
    private final List<Observer> Observers= new ArrayList<>();

    public List<ScapeRoom> getAll() {
        List<ScapeRoom> list = new ArrayList<>();
        ResultSet rs = executor.executeQueryWithResult("SELECT * FROM scaperoom");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new ScapeRoom(id, name));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(ScapeRoom sr) {
        String sql = "INSERT INTO scaperoom (name) VALUES (?)";
        executor.executeUpdate(sql, sr.getName());
        NotificationService.getInstance().notifyObservers("SCAPEROOM_CREATED", sr);
    }

    public void update(ScapeRoom sr) {
        String sql = "UPDATE scaperoom SET name = ? WHERE id = ?";
        executor.executeUpdate(sql, sr.getName(), String.valueOf(sr.getId()));
        NotificationService.getInstance().notifyObservers("SCAPEROOM_UPDATED", sr);
    }

    public void delete(int id) {
        String sql = "DELETE FROM scaperoom WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
        NotificationService.getInstance().notifyObservers("SCAPEROOM_DELETED", new ScapeRoom(id,null));
    }

    @Override
    public void attach(Observer observer){
        Observers.add(observer);
    }
    @Override
    public void detach(Observer observer){
        Observers.remove(observer);
    }
    @Override
    public void notifyObservers(String event, Object data){
        for(Observer observer:Observers){
            observer.update(event,data);
        }
    }
    @Override
    public List<Observer> getObservers() {
        return Observers;
    }
}
