package controller;

import database.Database;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Clients;
import model.CreditContracts;
import model.CreditTypes;
import model.Payments;
import model.User;
import model.Users;
import view.AdminMainView;
import view.ClientMainView;
import view.LoginView;
import view.RegisterView;

import java.sql.SQLException;

public class LoginController {
    private final LoginView view;
    private final Clients clientsModel;
    private final Users usersModel;
    private final CreditTypes creditTypesModel;
    private final CreditContracts creditContractsModel;
    private final Payments paymentsModel;

    public LoginController(LoginView view, Clients clientsModel, Users usersModel, CreditTypes creditTypesModel, CreditContracts creditContractsModel, Payments paymentsModel) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.usersModel = usersModel;
        this.creditTypesModel = creditTypesModel;
        this.creditContractsModel = creditContractsModel;
        this.paymentsModel = paymentsModel;

        view.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    onLogin();
                } catch (SQLException e) {
                    ;
                }
            }
        });
        view.getRegisterButton().setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                RegisterView registerView = new RegisterView();
                Stage regStage = new Stage();
                registerView.start(regStage);
                new RegisterController(registerView, clientsModel, usersModel);
            }
        });

    }

    private void onLogin() throws SQLException {
        String login = view.getLogin().getText().trim();
        String pass = view.getPassField().getText().trim();

        User user = Database.loadUser(login);
        if (user == null || !user.getPassword().equals(pass)) {
            view.error("Неверный логин или пароль");
            return;
        }

        clientsModel.setAll(Database.loadClients(user));
        usersModel.setAll(Database.loadUsers(user));
        creditTypesModel.setAll(Database.loadCreditTypes());
        int filterClientId = "client".equals(user.getRole()) ? user.getClientId() : 0;
        creditContractsModel.setAll(Database.loadCreditContracts(user, filterClientId));
        paymentsModel.setAll(Database.loadPayments(user, filterClientId));
        Stage loginStage = (Stage) view.getLoginButton().getScene().getWindow();
        loginStage.close();
        if ("client".equals(user.getRole())) {
            ClientMainView clientView = new ClientMainView();
            Stage clientStage = new Stage();
            clientView.start(clientStage);
            new ClientMainController(clientStage, clientView, clientsModel, creditTypesModel, creditContractsModel, paymentsModel, user.getClientId(), user
            );
        } else if ("admin".equals(user.getRole())) {
            AdminMainView adminView = new AdminMainView();
            Stage adminStage = new Stage();
            adminView.start(adminStage);
            new AdminMainController(adminStage, adminView, clientsModel, creditTypesModel, creditContractsModel, paymentsModel, user);
        }
    }
}
