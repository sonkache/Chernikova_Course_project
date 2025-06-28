import model.Clients;
import model.Users;
import model.CreditTypes;
import model.CreditContracts;
import model.Payments;
import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;
import controller.LoginController;

public class Main extends Application {
    private final Clients clientsModel = new Clients();
    private final Users usersModel = new Users();
    private final CreditTypes creditTypesModel = new CreditTypes();
    private final CreditContracts creditContractsModel = new CreditContracts();
    private final Payments paymentsModel = new Payments();

    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView, clientsModel, usersModel, creditTypesModel, creditContractsModel, paymentsModel);
        loginView.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
