package ConnectionDB;

import java.sql.*;

public class SQLExecutor {

    public void executeQuery(String sql) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    public void executeUpdate(String sql, String... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] == null) {
                    stmt.setNull(i + 1, java.sql.Types.NULL);  // Esto es importante
                } else {
                    stmt.setString(i + 1, params[i]);
                }
            }
            int rows = stmt.executeUpdate();
            System.out.println("✅ Operation successful. Rows affected: " + rows);
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}