package controller;

import database.Database;
import model.Client;
import model.Clients;
import model.User;
import model.Users;
import view.RegisterView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.*;

public class RegisterController {
    private final RegisterView view;
    private final Clients clientsModel;
    private final Users usersModel;

    public RegisterController(RegisterView view, Clients clientsModel, Users usersModel) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.usersModel = usersModel;

        view.getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onSubmit();
            }
        });
    }

    private void onSubmit() {
        String name = view.getNameField().getText().trim();
        String prop = view.getPropertyField().getText().trim();
        String addr = view.getAddressField().getText().trim();
        String phone = view.getPhoneField().getText().trim();
        String contact = view.getContactField().getText().trim();
        String login = view.getLoginField().getText().trim();
        String password = view.getPassField().getText().trim();

        try (Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);
            String sqlClient = "INSERT INTO clients (name, property_type, address, phone_number, contact_person) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlClient, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, name);
                ps1.setString(2, prop);
                ps1.setString(3, addr);
                ps1.setString(4, phone);
                ps1.setString(5, contact);
                ps1.executeUpdate();
                try (ResultSet keys = ps1.getGeneratedKeys()) {
                    int newClientId = keys.getInt(1);
                    String sqlUser = "INSERT INTO users (client_id, login, password, role) VALUES (?, ?, ?, 'client')";
                    try (PreparedStatement ps2 = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                        ps2.setInt(1, newClientId);
                        ps2.setString(2, login);
                        ps2.setString(3, password);
                        ps2.executeUpdate();
                        try (ResultSet keys2 = ps2.getGeneratedKeys()) {
                            int newUserId = keys2.getInt(1);
                            Client client = new Client(newClientId, name, prop, addr, phone, contact);
                            clientsModel.add(client);
                            User user = new User(newUserId, newClientId, login, password, "client");
                            usersModel.add(user);
                            conn.commit();
                            view.showMessage("Регистрация прошла успешно");
                            Stage stage = (Stage) view.getSubmitButton().getScene().getWindow();
                            stage.close();
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            try {
                Database.getConnection().rollback();
            } catch (SQLException ignored) {}
            view.showMessage("Ошибка регистрации: " + ex.getMessage());
        }
    }
}