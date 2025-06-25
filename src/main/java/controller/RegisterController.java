package controller;

import database.Database;
import model.*;
import view.RegisterView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.*;

/**
 * Контроллер окна регистрации:
 * 1. Вставляет нового клиента в таблицу clients,
 * 2. Потом — нового пользователя с ролью "client",
 * 3. Обновляет модели и закрывает окно.
 */
public class RegisterController {
    private final RegisterView view;
    private final Clients clientsModel;
    private final Users usersModel;

    // Конструктор для инициализации контроллера
    public RegisterController(RegisterView view, Clients clientsModel, Users usersModel) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.usersModel = usersModel;

        // Навешиваем обработчик на кнопку "Зарегистрироваться"
        view.getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onSubmit();  // Вызов метода обработки регистрации
            }
        });
    }

    /**
     * Метод-обработчик нажатия кнопки "Зарегистрироваться"
     */
    private void onSubmit() {
        String name = view.getNameField().getText().trim();
        String prop = view.getPropertyField().getText().trim();
        String addr = view.getAddressField().getText().trim();
        String phone = view.getPhoneField().getText().trim();
        String contact = view.getContactField().getText().trim();
        String login = view.getLoginField().getText().trim();
        String password = view.getPassField().getText().trim();

        // Проверка обязательных полей
        if (name.isEmpty() || login.isEmpty() || password.isEmpty()) {
            view.showMessage("Имя, логин и пароль — обязательны");
            return;
        }

        // Подключаемся к базе данных
        try (Connection conn = Database.getConnection()) {
            // Вставка нового клиента в таблицу clients
            String sqlClient = "INSERT INTO clients "
                    + "(name, property_type, address, phone_number, contact_person) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(
                    sqlClient, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, name);
            ps1.setString(2, prop);
            ps1.setString(3, addr);
            ps1.setString(4, phone);
            ps1.setString(5, contact);
            ps1.executeUpdate();

            // Получаем ID нового клиента
            ResultSet keys = ps1.getGeneratedKeys();
            if (!keys.next()) throw new SQLException("Нет ID нового клиента");
            int newClientId = keys.getInt(1);

            // Вставка нового пользователя в таблицу users
            String sqlUser = "INSERT INTO users (client_id, login, password, role) VALUES (?, ?, ?, 'client')";
            PreparedStatement ps2 = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            ps2.setInt(1, newClientId);
            ps2.setString(2, login);
            ps2.setString(3, password);
            ps2.executeUpdate();

            // Получаем ID нового пользователя
            ResultSet keys2 = ps2.getGeneratedKeys();
            keys2.next();
            int newUserId = keys2.getInt(1);

            // Обновляем модели (добавляем новые объекты)
            Client client = new Client(newClientId, name, prop, addr, phone, contact);
            clientsModel.add(client);

            User user = new User(newUserId, newClientId, login, password, "client");
            usersModel.add(user);

            // Закрываем окно регистрации
            Stage stage = (Stage) view.getSubmitButton().getScene().getWindow();
            stage.close();

        } catch (SQLException ex) {
            view.showMessage("Ошибка регистрации: " + ex.getMessage());  // Выводим сообщение об ошибке
        }
    }
}
