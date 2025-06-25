package controller;

import model.Clients;
import model.CreditContracts;
import model.CreditTypes;
import model.Payments;
import view.AdminMainView;
import javafx.stage.Stage;
public class AdminMainController {
    public AdminMainController(Stage stage, AdminMainView view, Clients clientsModel, CreditTypes creditTypesModel, CreditContracts contractsModel, Payments paymentsModel) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.creditTypesModel = creditTypesModel;
        this.contractsModel = contractsModel;
        this.paymentsModel = paymentsModel;
        view.start(stage);
        setUpTabControllers();
    }

    private void setUpTabControllers() {
        new AdminClientsController(view.getClientsView(), clientsModel);
        new AdminCreditTypesController(view.getCreditTypesView(), creditTypesModel);
        new AdminIssueCreditController(view.getIssueCreditView(), clientsModel, creditTypesModel, contractsModel);
        new AdminPaymentHistoryController(view.getHistoryView(), contractsModel, paymentsModel);
    }

    private final AdminMainView view;
    private final Clients clientsModel;
    private final CreditTypes creditTypesModel;
    private final CreditContracts contractsModel;
    private final Payments paymentsModel;
}
