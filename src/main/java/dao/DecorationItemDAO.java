package dao;

import ConnectionDB.SQLExecutor;
import front.model.DecorationItem;
import front.observer.NotificationService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DecorationItemDAO {
    private final SQLExecutor executor = new SQLExecutor();

    public List<DecorationItem> getAll() {
        List<DecorationItem> list = new ArrayList<>();
        ResultSet rs = executor.executeQueryWithResult("SELECT * FROM decorationitem");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int itemId = rs.getInt("item_id");
                String material = rs.getString("material");
                String theme = rs.getString("theme");
                double price = rs.getDouble("price");
                list.add(new DecorationItem(id, itemId, material, theme, price));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(DecorationItem di) {
        String sql = "INSERT INTO decorationitem (item_id, material, theme, price) VALUES (?, ?, ?, ?)";
        executor.executeUpdate(sql,
            String.valueOf(di.getItemId()),
            di.getMaterial(),
            di.getTheme(),
            String.valueOf(di.getPrice()));
        NotificationService.getInstance().notifyObservers("DECORATION_ITEM_CREATED", di);
    }

    public void update(DecorationItem di) {
        String sql = "UPDATE decorationitem SET item_id = ?, material = ?, theme = ?, price = ? WHERE id = ?";
        executor.executeUpdate(sql,
            String.valueOf(di.getItemId()),
            di.getMaterial(),
            di.getTheme(),
            String.valueOf(di.getPrice()),
            String.valueOf(di.getId()));
        NotificationService.getInstance().notifyObservers("DECORATION_ITEM_UPDATED", di);
    }

    public void delete(int id) {
        String sql = "DELETE FROM decorationitem WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
        NotificationService.getInstance().notifyObservers("DECORATION_ITEM_DELETED", new DecorationItem(id, 0, null, null, null, null, 0.0));
    }
}
