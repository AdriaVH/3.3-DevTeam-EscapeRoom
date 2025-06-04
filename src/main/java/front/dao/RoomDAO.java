package front.dao;

import Back.models.Room;
import ConnectionDB.SQLExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final SQLExecutor executor = new SQLExecutor();

    /*public List<Room> getAll() {
        List<Room> list = new ArrayList<>();
        ResultSet rs = executor.executeQueryWithResult("SELECT * FROM room");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int srId = rs.getInt("scape_room_id");
                String dl = rs.getString("difficult_level");
                String theme = rs.getString("theme");
                list.add(new Room(id, srId, dl, theme));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }*/

    public void create(Room r) {
        String sql = "INSERT INTO room (scape_room_id, difficult_level, theme) VALUES (?, ?, ?)";
        executor.executeUpdate(sql,
            String.valueOf(r.getScapeRoom_Id()),
            String.valueOf(r.getDifficult()),
            String.valueOf(r.getTheme()));
    }

    public void update(Room r) {
        String sql = "UPDATE room SET scape_room_id = ?, difficult_level = ?, theme = ? WHERE id = ?";
        executor.executeUpdate(sql,
            String.valueOf(r.getScapeRoom_Id()),
                String.valueOf(r.getDifficult()),
                String.valueOf(r.getTheme()),
            String.valueOf(r.getId()));
    }

    public void delete(int id) {
        String sql = "DELETE FROM room WHERE id = ?";
        executor.executeUpdate(sql, String.valueOf(id));
    }
}
