package repository.dao;

import model.Enigma;
import java.util.List;

public interface EnigmaDAO {
    void insert(Enigma obj);
    List<Enigma> findAll();
    void update(Enigma obj);
    void delete(int id);
    Enigma findById(int id);
}