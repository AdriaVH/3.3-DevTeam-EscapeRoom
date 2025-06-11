package repository.dao;

import model.User;
import java.util.List;

public interface UserDAO {
    void insert(User user);
    void update(User user);
    void delete(int id);
    User findById(int id);
    List<User> findAll();
}