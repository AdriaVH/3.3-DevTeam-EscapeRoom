package repository.dao;

import model.Player;

import java.util.List;

public interface PlayerDAO {
    void insert(Player player);
    void update(Player player);
    void delete(int id);
    Player findById(int id);
    List<Player> findAll();
}