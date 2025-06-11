package repository.dao;

import model.User;
import db.SQLExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOSQL implements UserDAO {

    private  final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO user (mail, name) VALUES (?, ?)";
        executor.executeUpdate(sql, user.getMail(), user.getName());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET mail = ?, name = ? WHERE id = ?";
        executor.executeUpdate(sql, user.getMail(), user.getName(), user.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        executor.executeUpdate(sql, id);
    }

    @Override
    public  User findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (ResultSet rs = executor.executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("mail"),
                        rs.getString("name")
                );
            }
        } catch (Exception e) {
            System.out.println("Error findById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        ResultSet rs = executor.executeQuery("SELECT * FROM user");

        if (rs == null) {
            System.err.println("⚠️ UserDAOSQL.findAll(): ResultSet is null.");
            return users;
        }

        try {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("mail"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error reading users: " + e.getMessage());
        }
        return users;
    }
}

