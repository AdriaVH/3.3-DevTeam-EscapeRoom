package repository.dao;

import db.SQLExecutor;
import enums.Theme;
import model.DecorationItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DecorationItemDAOSQL implements DecorationItemDAO {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public void insert(DecorationItem obj) {
        executor.executeUpdate(
                "INSERT INTO decorationitem (name, room_id, material, theme, description, price) VALUES (?, ?, ?, ?, ?, ?)",
                obj.getName(),
                obj.getRoomId(),
                obj.getMaterial().name(),
                obj.getTheme().name(),
                obj.getDescription(),
                obj.getPrice()
        );
    }

    @Override
    public List<DecorationItem> findAll() {
        List<DecorationItem> items = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM decorationitem");

        if (rs == null) {
            System.err.println("⚠️ DecorationItemDAOSQL.findAll(): ResultSet is null.");
            return items;
        }

        try {
            while (rs.next()) {
                DecorationItem item = new DecorationItem(
                rs.getInt("id"),
                rs.getInt("room_Id"),
                rs.getString("name"),
                DecorationItem.Material.valueOf(rs.getString("material")),
                Theme.valueOf(rs.getString("theme")),
                rs.getString("description"),
                rs.getBigDecimal("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading decoration items: " + e.getMessage());
        }

        return items;
    }

    @Override
    public void update(DecorationItem obj) {
        executor.executeUpdate(
                "UPDATE decorationitem SET name = ?, room_id = ?, material = ?, theme = ?, description = ?, price = ? WHERE id = ?",
                obj.getName(),
                obj.getRoomId(),
                obj.getMaterial().name(),
                obj.getTheme().name(),
                obj.getDescription(),
                obj.getPrice(),
                obj.getId()
        );
    }

    @Override
    public void delete(int id) {
        executor.executeUpdate("DELETE FROM decorationitem WHERE id = ?", id);
    }
}