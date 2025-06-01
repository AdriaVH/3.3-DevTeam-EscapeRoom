package ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDatabase {
    public void ShowDatabase() {
        String sql = "SELECT * FROM client";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("client_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                System.out.println("ID: " + id + ", Name: " + first_name + ", Surname: " + last_name);
            }

        } catch (SQLException e) {
            System.err.println("Error reading: " + e.getMessage());
        }
    }
}