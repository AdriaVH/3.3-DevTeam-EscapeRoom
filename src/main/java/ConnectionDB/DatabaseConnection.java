package ConnectionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final EnvLoader env = EnvLoader.getInstance();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                env.getDbUrl(),
                env.getDbUser(),
                env.getDbPassword()
        );
    }
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Database connection successful!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            return false;
        }
    }
}
