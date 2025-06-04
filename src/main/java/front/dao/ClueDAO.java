package front.dao;

import Back.models.Clue;
import ConnectionDB.DatabaseConnection;
import ConnectionDB.SQLExecutor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClueDAO {
    private final SQLExecutor executor = new SQLExecutor();

    public List<Clue> getAll() {
        List<Clue> list = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM clue");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int enigmaId = rs.getInt("enigma_id");
                int itemId = rs.getInt("item_id");
                String theme = rs.getString("theme");
                BigDecimal price = rs.getBigDecimal("price");
                list.add(new Clue(id, enigmaId, itemId, theme, price));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(Clue c) {

        DatabaseConnection.stablishConnection();

        String sql = "INSERT INTO clue (enigma_id, item_id, theme, price) VALUES (?, ?, ?, ?)";
        executor.executeUpdate(sql,
            String.valueOf(c.getEnigma_Id()),
            String.valueOf(c.getItem_Id()),
                String.valueOf(c.getTheme()),
            String.valueOf(c.getPrice()));
    }

    public void update(Clue c) {
        String sql = "UPDATE clue SET enigma_id = ?, item_id = ?, theme = ?, price = ? WHERE id = ?";
        executor.executeUpdate(sql,
            String.valueOf(c.getEnigma_Id()),
            String.valueOf(c.getItem_Id()),
            String.valueOf(c.getTheme()),
            String.valueOf(c.getPrice()),
            String.valueOf(c.getId()));
    }

    public void delete(int id) {
        String sql = "DELETE FROM clue WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
    }
}
