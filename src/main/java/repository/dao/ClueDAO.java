package repository.dao;

import model.Clue;
import java.util.List;

public interface ClueDAO {
    void insert(Clue obj);
    List<Clue> findAll();
    void update(Clue obj);
    void delete(int id);
    Clue findById(int id);
}