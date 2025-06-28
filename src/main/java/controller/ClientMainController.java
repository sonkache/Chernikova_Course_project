package controller;

import model.*;
import view.ClientMainView;
import javafx.stage.Stage;
import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientMainController {
    private final ClientMainView view;
    private final Clients clientsModel;
    private final CreditTypes typesModel;
    private final CreditContracts contractsModel;
    private final Payments paymentsModel;
    private final int clientId;
    private final User user;
    public ClientMainController(Stage stage, ClientMainView view, Clients clientsModel, CreditTypes typesModel, CreditContracts contractsModel, Payments paymentsModel, int clientId, User user) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.typesModel = typesModel;
        this.contractsModel = contractsModel;
        this.paymentsModel = paymentsModel;
        this.clientId = clientId;
        this.user = user;
        view.start(stage);
        new ClientDataController(view.getDataView(), clientsModel, clientId);
        Clients singleClient = new Clients();
        List<Client> filteredClients = new ArrayList<>();
        for (Client c : clientsModel.getAll()) {
            if (c.getId() == clientId) {
                filteredClients.add(c);
            }
        }
        singleClient.setAll(filteredClients);

        new AdminIssueCreditController(view.getIssueCreditView(), singleClient, typesModel, contractsModel, user);
        new ClientCreditTypesController(view.getTypesView(), typesModel);
        new ClientCreditsController(view.getCreditsView(), contractsModel, clientId);
        try {
            paymentsModel.setAll(Database.loadPayments(user, clientId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new ClientPaymentsController(view.getPaymentsView(), paymentsModel, contractsModel, clientId);
    }
}
