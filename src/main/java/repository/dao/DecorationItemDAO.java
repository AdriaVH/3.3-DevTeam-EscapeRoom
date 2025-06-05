package repository.dao;

import model.DecorationItem;
import java.util.List;

public interface DecorationItemDAO {
    void insert(DecorationItem obj);
    List<DecorationItem> findAll();
    void update(DecorationItem obj);
    void delete(int id);
}