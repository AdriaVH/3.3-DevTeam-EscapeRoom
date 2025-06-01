package ConnectionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = ConfigReader.get("db.url");
        String user = ConfigReader.get("db.user");
        String password = ConfigReader.get("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}