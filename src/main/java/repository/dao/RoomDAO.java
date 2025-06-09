package repository.dao;

import model.Room;
import java.util.List;

public interface RoomDAO {
    void insert(Room obj);
    List<Room> findAll();
    void update(Room obj);
    void delete(int id);
    Room findById(int id);
}