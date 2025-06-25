package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bank_credits?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    private Database() {
        ;
    }

    public static List<Client> loadClients() throws SQLException {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT id, name, property_type, address, phone_number, contact_person FROM clients";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                list.add(new Client(result.getInt("id"), result.getString("name"), result.getString("property_type"), result.getString("address"), result.getString("phone_number"), result.getString("contact_person")));
            }
        } catch (SQLException e) {
           ;
        }
        return list;
    }

    public static List<User> loadUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id, client_id, login, password, role FROM users";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                list.add(new User(result.getInt("id"), result.getInt("client_id"), result.getString("login"), result.getString("password"), result.getString("role")));
            }
        } catch (SQLException e) {
            ;
        }
        return list;
    }

    public static List<CreditType> loadCreditTypes() throws SQLException {
        List<CreditType> list = new ArrayList<>();
        String sql = "SELECT id, name, conditions, percent, return_period FROM credit_types";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                list.add(new CreditType(result.getInt("id"), result.getString("name"), result.getString("conditions"), result.getDouble("percent"), result.getInt("return_period")));
            }
        } catch (SQLException e) {
            ;
        }
        return list;
    }

    public static List<CreditContract> loadCreditContracts() throws SQLException {
        List<CreditContract> list = new ArrayList<>();
        String sql = "SELECT id, client_id, credit_type_id, amount, issue_date, return_date FROM credit_contracts";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                list.add(new CreditContract(result.getInt("id"), result.getInt("client_id"), result.getInt("credit_type_id"), result.getDouble("amount"), result.getDate("issue_date").toLocalDate(), result.getDate("return_date").toLocalDate()));
            }
        } catch (SQLException e) {
            ;
        }
        return list;
    }

    public static List<Payment> loadPayments() throws SQLException {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT credit_contract_id, amount, penalty, payment_date FROM payments";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                list.add(new Payment(result.getInt("credit_contract_id"), result.getDouble("amount"), result.getDouble("penalty"), result.getDate("payment_date").toLocalDate()));
            }
        } catch (SQLException e) {
           ;
        }
        return list;
    }
}
