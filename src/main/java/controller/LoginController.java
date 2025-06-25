package controller;

import model.Clients;
import model.CreditContracts;
import model.CreditTypes;
import model.Payments;
import model.User;
import model.Users;
import view.ClientMainView;
import view.LoginView;
import view.RegisterView;
import view.AdminMainView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
                onLogin();
            }
        });

        view.getRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterView registerView = new RegisterView();
                Stage regStage = new Stage();
                registerView.start(regStage);
                new RegisterController(registerView, clientsModel, usersModel);
            }
        });
    }
    private void onLogin() {
        String login = view.getLogin().getText().trim();
        String pass = view.getPassField().getText().trim();
        User user = null;
        for (User u : usersModel.getAll()) {
            if (u.getLogin().equals(login) && u.getPassword().equals(pass)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            view.error("Неверный логин или пароль");
            return;
        }

        Stage stage = (Stage) view.getLoginButton().getScene().getWindow();
        stage.close();

        if ("client".equals(user.getRole())) {
            ClientMainView clientView = new ClientMainView();
            Stage clientStage = new Stage();
            clientView.start(clientStage);
            new ClientMainController(clientStage, clientView, clientsModel, creditContractsModel, paymentsModel, creditTypesModel, user.getClientId());
        } else {
            AdminMainView adminView = new AdminMainView();
            Stage adminStage = new Stage();
            adminView.start(adminStage);
            new AdminMainController(adminStage, adminView, clientsModel, creditTypesModel, creditContractsModel, paymentsModel);
        }
    }
}
