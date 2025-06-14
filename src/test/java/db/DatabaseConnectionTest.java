    package db;

    import org.junit.jupiter.api.Test;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.Statement;

    import static org.junit.jupiter.api.Assertions.*;

    class DatabaseConnectionTest {

        @Test
        void testDatabaseConnection() {
            EnvLoader env = EnvLoader.getInstance();
            String url = env.get("DB_URL");
            String user = env.get("DB_USER");
            String password = env.get("DB_PASSWORD");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                assertNotNull(connection, "Connection shouldn't be null");
                assertFalse(connection.isClosed(), "Connection shouldn't be closed");
                System.out.println("✅ Connection done!!");
                Statement statement = connection.createStatement();
                statement.executeQuery("SELECT * FROM room");
            } catch (SQLException e) {
                fail("❌ Error: " + e.getMessage());
            }
        }
    }