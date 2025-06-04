package dao;

import ConnectionDB.SQLExecutor;
import front.model.Enigma;
import front.observer.NotificationService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnigmaDAO {
    private final SQLExecutor executor = new SQLExecutor();

    public List<Enigma> getAll() {
        List<Enigma> list = new ArrayList<>();
        ResultSet rs = executor.executeQueryWithResult("SELECT * FROM enigma");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int itemId = rs.getInt("item_id");
                String theme = rs.getString("theme");
                list.add(new Enigma(id, itemId, theme));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(Enigma e) {
        String sql = "INSERT INTO enigma (item_id, theme) VALUES (?, ?)";
        executor.executeUpdate(sql,
            String.valueOf(e.getItemId()),
            e.getTheme());
        NotificationService.getInstance().notifyObservers("ENIGMA_CREATED", e);
    }

    public void update(Enigma e) {
        String sql = "UPDATE enigma SET item_id = ?, theme = ? WHERE id = ?";
        executor.executeUpdate(sql,
            String.valueOf(e.getItemId()),
            e.getTheme(),
            String.valueOf(e.getId()));
        NotificationService.getInstance().notifyObservers("ENIGMA_UPDATED", e);
    }

    public void delete(int id) {
        String sql = "DELETE FROM enigma WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
        NotificationService.getInstance().notifyObservers("ENIGMA_DELETED", new Enigma(id,0,null,null,null));
    }
}
