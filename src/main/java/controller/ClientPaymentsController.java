package controller;

import model.CreditContract;
import model.CreditContracts;
import model.Payment;
import model.Payments;
import view.ClientPaymentsView;
import javafx.collections.FXCollections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientPaymentsController {
    private final ClientPaymentsView view;
    private final Payments paymentsModel;
    private final CreditContracts contractsModel;
    private final int clientId;

    public ClientPaymentsController(ClientPaymentsView view, Payments paymentsModel, CreditContracts contractsModel, int clientId) {
        this.view = view;
        this.paymentsModel = paymentsModel;
        this.contractsModel = contractsModel;
        this.clientId = clientId;
        update();
        paymentsModel.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
        contractsModel.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }
    private void update() {
        Set<Integer> myContracts = new HashSet<>();
        List<CreditContract> contracts = contractsModel.getAll();
        for (int i = 0; i < contracts.size(); i++) {
            if (contracts.get(i).getClientId() == clientId) {
                myContracts.add(contracts.get(i).getId());
            }
        }

        List<Payment> list = paymentsModel.getAll();
        List<Payment> filteredPayments = new java.util.ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (myContracts.contains(list.get(i).getCreditContractId())) {
                filteredPayments.add(list.get(i));
            }
        }
        view.getPayments().setItems(FXCollections.observableArrayList(filteredPayments));
    }
}
