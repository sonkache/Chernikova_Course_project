package database;

import model.*;

import java.sql.*;
import java.time.LocalDate;
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

    public static List<Client> loadClients(User user) throws SQLException {
        String sql;
        if ("admin".equals(user.getRole())) {
            sql = "SELECT id, name, property_type, address, phone_number, contact_person FROM clients";
        } else {
            sql = "SELECT id, name, property_type, address, phone_number, contact_person FROM clients WHERE id = ?";
        }

        List<Client> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (!"admin".equals(user.getRole())) {
                stmt.setInt(1, user.getClientId());
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Client(rs.getInt("id"), rs.getString("name"), rs.getString("property_type"), rs.getString("address"), rs.getString("phone_number"), rs.getString("contact_person")));
                }
            }
        }
        return list;
    }

    public static List<User> loadUsers(User user) throws SQLException {
        String sql;
        if ("admin".equals(user.getRole())) {
            sql = "SELECT id, client_id, login, password, role FROM users";
        } else {
            sql = "SELECT id, client_id, login, password, role FROM users WHERE client_id = ?";
        }

        List<User> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (!"admin".equals(user.getRole())) {
                stmt.setInt(1, user.getClientId());
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new User(rs.getInt("id"), rs.getInt("client_id"), rs.getString("login"), rs.getString("password"), rs.getString("role")));
                }
            }
        }
        return list;
    }

    public static List<CreditType> loadCreditTypes() throws SQLException {
        String sql = "SELECT id, name, conditions, percent, return_period FROM credit_types";
        List<CreditType> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new CreditType(rs.getInt("id"), rs.getString("name"), rs.getString("conditions"), rs.getDouble("percent"), rs.getInt("return_period")));
            }
        }
        return list;
    }

    public static List<CreditContract> loadCreditContracts(User user, int clientId) throws SQLException {
        String sql;
        if ("admin".equals(user.getRole())) {
            sql = "SELECT id, client_id, credit_type_id, amount, issue_date, return_date FROM credit_contracts";
        } else {
            sql = "SELECT id, client_id, credit_type_id, amount, issue_date, return_date FROM credit_contracts WHERE client_id = ?";
        }

        List<CreditContract> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (!"admin".equals(user.getRole())) {
                stmt.setInt(1, clientId);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new CreditContract(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("credit_type_id"), rs.getDouble("amount"), rs.getDate("issue_date").toLocalDate(), rs.getDate("return_date").toLocalDate()));
                }
            }
        }
        return list;
    }


    public static List<Payment> loadPayments(User user, int clientId) throws SQLException {
        List<Payment> list = new ArrayList<>();
        String sql;
        if ("admin".equals(user.getRole())) {
            sql = "SELECT credit_contract_id, amount, penalty, payment_date FROM payments";
        } else {
            sql = "SELECT p.credit_contract_id, p.amount, p.penalty, p.payment_date FROM payments p JOIN credit_contracts c ON p.credit_contract_id = c.id WHERE c.client_id = ?";
        }
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (!"admin".equals(user.getRole())) {
                stmt.setInt(1, clientId);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Payment(rs.getInt("credit_contract_id"), rs.getDouble("amount"),rs.getDouble("penalty"),
                        rs.getDate("payment_date").toLocalDate()));
            }
        }
        return list;
    }


    public static User loadUser(String login) throws SQLException {
        String sql = "SELECT id, client_id, login, password, role FROM users WHERE login = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getInt("client_id"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
                } else {
                    return null;
                }
            }
        }
    }


    public static void updateClient(Client client) throws SQLException {
        String sql = "UPDATE clients SET name = ?, property_type = ? address = ?, phone_number = ?, contact_person = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPropertyType());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getPhoneNumber());
            stmt.setString(5, client.getContactPerson());
            stmt.setInt(6, client.getId());
            stmt.executeUpdate();
        }
    }

    public static void insertPayment(int contractId, double amount, LocalDate date) throws SQLException {
        String sql = "INSERT INTO payments (credit_contract_id, amount, penalty, payment_date) VALUES (?, ?, 0, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contractId);
            stmt.setDouble(2, amount);
            stmt.setDate(3, Date.valueOf(date));
            stmt.executeUpdate();
        }
    }
}
