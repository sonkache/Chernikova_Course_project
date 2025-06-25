package controller;

import view.ClientMainView;
import model.*;
import javafx.stage.Stage;

public class ClientMainController {
    public ClientMainController(Stage stage, ClientMainView view, Clients clientsModel, CreditContracts contractsModel, Payments paymentsModel, CreditTypes typesModel, int clientId) {
        view.start(stage);
        new ClientDataController(view.getDataView(), clientsModel, clientId);
        new ClientCreditsController(view.getCreditsView(), contractsModel, clientId);
        new ClientPaymentsController(view.getPaymentsView(), paymentsModel, contractsModel, clientId);
        new ClientCreditTypesController(view.getTypesView(), typesModel);
    }
}
