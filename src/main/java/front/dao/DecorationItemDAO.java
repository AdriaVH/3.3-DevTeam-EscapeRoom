package front.dao;

import Back.models.DecorationItem;

import ConnectionDB.SQLExecutor;

public class DecorationItemDAO {
    private final SQLExecutor executor = new SQLExecutor();

    /*public List<DecorationItem> getAll() {
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
    }*/

    public void insert(DecorationItem di) {
        String sql = "INSERT INTO decorationitem (item_id, material, theme, price) VALUES (?, ?, ?, ?)";
        executor.executeUpdate(sql,
                String.valueOf(di.getId()),
                String.valueOf(di.getMaterial()),
                String.valueOf(di.getTheme()),
            String.valueOf(di.getPrice()));
    }

    public void update(DecorationItem di) {
        String sql = "UPDATE decorationitem SET item_id = ?, material = ?, theme = ?, price = ? WHERE id = ?";
        executor.executeUpdate(sql,
            String.valueOf(di.getId()),
            String.valueOf(di.getMaterial()),
            String.valueOf(di.getTheme()),
            String.valueOf(di.getPrice()),
            String.valueOf(di.getId()));
    }

    public void delete(int id) {
        String sql = "DELETE FROM decorationitem WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
    }
}
