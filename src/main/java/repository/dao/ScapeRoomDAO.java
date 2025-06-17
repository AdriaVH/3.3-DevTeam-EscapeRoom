package repository.dao;

import model.ScapeRoom;
import java.util.List;

public interface ScapeRoomDAO {
    void insert(ScapeRoom obj);
    List<ScapeRoom> findAll();
    void update(ScapeRoom obj);
    void delete(int id);
    ScapeRoom findById(int id);
    public double totalPrice (int id);

}