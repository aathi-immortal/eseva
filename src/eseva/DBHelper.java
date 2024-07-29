

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:eseva.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "contact TEXT NOT NULL" +
                ");";

        String createComplaintsTable = "CREATE TABLE IF NOT EXISTS complaints (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," +
                "description TEXT," +
                "status TEXT," +
                "FOREIGN KEY(userId) REFERENCES users(id)" +
                ");";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createComplaintsTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
