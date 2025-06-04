package ConnectionDB;

import observer.NotificationService;

import java.sql.*;

public class SQLExecutor {

    public ResultSet executeQuery(String sql) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            /*int cols = meta.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "\t");
                }
                System.out.println();
            }*/
            return rs;

        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
            return null;
        }
    }

    public void executeUpdate(String sql, String strng,Object c,String... params) {
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
            NotificationService.getInstance().notifyObservers(strng,c);
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}