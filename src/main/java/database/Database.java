package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL      = "jdbc:mysql://localhost:3306/bank_credits?serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
    private Database() { }
}
