package front.dao;

import Back.models.*;
import ConnectionDB.SQLExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScapeRoomDAO {
    private final SQLExecutor executor = new SQLExecutor();

    /*public List<ScapeRoom> getAll() {
        List<ScapeRoom> list = new ArrayList<>();
        ResultSet rs = executor.executeQueryWithResult("SELECT * FROM scaperoom");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new ScapeRoom(id, name));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }*/

    public void create(ScapeRoom sr) {
        String sql = "INSERT INTO scaperoom (name) VALUES (?)";
        executor.executeUpdate(sql, sr.getName());
    }

    public void update(ScapeRoom sr) {
        String sql = "UPDATE scaperoom SET name = ? WHERE id = ?";
        executor.executeUpdate(sql, sr.getName(), String.valueOf(sr.getId()));
    }

    public void delete(int id) {
        String sql = "DELETE FROM scaperoom WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
    }
}
