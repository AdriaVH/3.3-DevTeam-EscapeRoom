package dao;

import ConnectionDB.SQLExecutor;
import front.model.Clue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClueDAO {
    private final SQLExecutor executor = new SQLExecutor();

    public List<Clue> getAll() {
        List<Clue> list = new ArrayList<>();
        ResultSet rs = executor.executeQueryWithResult("SELECT * FROM clue");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int enigmaId = rs.getInt("enigma_id");
                int itemId = rs.getInt("item_id");
                String theme = rs.getString("theme");
                double price = rs.getDouble("price");
                list.add(new Clue(id, enigmaId, itemId, theme, price));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(Clue c) {
        String sql = "INSERT INTO clue (enigma_id, item_id, theme, price) VALUES (?, ?, ?, ?)";
        executor.executeUpdate(sql,
                "CLUE_CREATED",c,
            String.valueOf(c.getEnigmaId()),
            String.valueOf(c.getItemId()),
            c.getTheme(),
            String.valueOf(c.getPrice()));
    }

    public void update(Clue c) {
        String sql = "UPDATE clue SET enigma_id = ?, item_id = ?, theme = ?, price = ? WHERE id = ?";
        executor.executeUpdate(sql,
            String.valueOf(c.getEnigmaId()),
            String.valueOf(c.getItemId()),
            c.getTheme(),
            String.valueOf(c.getPrice()),
            String.valueOf(c.getId()));
    }

    public void delete(int id) {
        String sql = "DELETE FROM clue WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));

    }
}
