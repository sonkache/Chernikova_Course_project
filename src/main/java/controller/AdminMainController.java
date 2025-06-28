package controller;

import javafx.stage.Stage;
import view.AdminMainView;
import model.Clients;
import model.CreditTypes;
import model.CreditContracts;
import model.Payments;
import model.User;

public class AdminMainController {
    private final AdminMainView view;
    private final Clients clientsModel;
    private final CreditTypes typesModel;
    private final CreditContracts contractsModel;
    private final Payments paymentsModel;
    private final User user;

    public AdminMainController(Stage stage, AdminMainView view, Clients clientsModel, CreditTypes typesModel, CreditContracts contractsModel, Payments paymentsModel, User user) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.typesModel = typesModel;
        this.contractsModel = contractsModel;
        this.paymentsModel = paymentsModel;
        this.user = user;
        view.start(stage);
        new AdminClientsController(view.getClientsView(), clientsModel);
        new AdminCreditTypesController(view.getCreditTypesView(), typesModel);
        new AdminIssueCreditController(view.getIssueCreditView(), clientsModel, typesModel, contractsModel, user);
        new AdminPaymentHistoryController(view.getHistoryView(), contractsModel, paymentsModel);
    }
}
