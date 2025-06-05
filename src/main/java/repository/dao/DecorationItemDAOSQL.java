package repository.dao;

import db.SQLExecutor;
import enums.Material;
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
                "INSERT INTO decorationitem (material, theme, description, price) VALUES (?, ?, ?, ?)",
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
                DecorationItem item = new DecorationItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setMaterial(Material.valueOf(rs.getString("material")));
                item.setTheme(Theme.valueOf(rs.getString("theme")));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getBigDecimal("price"));
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
                "UPDATE decorationitem SET id = ?, material = ?, theme = ?, description = ?, price = ? WHERE id = ?",
                obj.getId(),
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