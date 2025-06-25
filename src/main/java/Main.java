import database.Database;
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
    private Clients clientsModel = new Clients();
    private Users usersModel = new Users();
    private CreditTypes creditTypesModel = new CreditTypes();
    private CreditContracts creditContractsModel = new CreditContracts();
    private Payments paymentsModel = new Payments();

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadData();
        LoginView view = new LoginView();
        LoginController controller = new LoginController(view, clientsModel, usersModel, creditTypesModel, creditContractsModel, paymentsModel);
        view.start(primaryStage);
    }

    private void loadData() {
        try {
            clientsModel.setAll(Database.loadClients());
            usersModel.setAll(Database.loadUsers());
            creditTypesModel.setAll(Database.loadCreditTypes());
            creditContractsModel.setAll(Database.loadCreditContracts());
            paymentsModel.setAll(Database.loadPayments());
        } catch (Exception e) {
            ;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
