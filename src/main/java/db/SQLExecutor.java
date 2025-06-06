package db;


import java.sql.*;

public class SQLExecutor {
    private static SQLExecutor instance;
    private Connection connection;

    private SQLExecutor() {
        refreshConnection();
    }

    public static SQLExecutor getInstance() {
        if (instance == null) {
            instance = new SQLExecutor();
        }
        return instance;
    }

    private void refreshConnection() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (Exception e) {
            System.err.println("❌ Could not refresh connection: " + e.getMessage());
        }
    }

    private void ensureConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("🔁 Reconnecting to database...");
            refreshConnection();
        }
    }

    public ResultSet executeQuery(String sql, Object... params) {
        try {
            ensureConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            setParameters(statement, params);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("❌ SQLExecutor.executeQuery failed: " + e.getMessage());
            return null;
        }
    }

    public int executeUpdate(String sql, Object... params) {
        try {
            ensureConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            setParameters(statement, params);
            int affectedRows = statement.executeUpdate();
            System.out.println("Rows modified: " +affectedRows);
            return affectedRows;
        } catch (SQLException e) {
            System.err.println("❌ SQLExecutor.executeUpdate failed: " + e.getMessage());
            return -1;
        }
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            Object p = params[i];
            if (p == null) {
                stmt.setNull(i + 1, Types.NULL);
            } else {
                stmt.setObject(i + 1, p);
            }
        }
    }

}
