package controller;

import model.CreditContract;
import model.CreditContracts;
import model.Payment;
import model.Payments;
import view.ClientPaymentsView;
import database.Database;
import javafx.scene.control.Alert;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
        view.getPayButton().setOnAction(e -> onPay());
        update();

    }

    private void update() {
        List<CreditContract> clientCredits = new java.util.ArrayList<>();
        for (CreditContract contract : contractsModel.getAll()) {
            if (contract.getClientId() == clientId) {
                clientCredits.add(contract);
            }
        }
        view.getContractCombo().getItems().setAll(clientCredits);
        java.util.Set<Integer> contractIds = new java.util.HashSet<>();
        for (CreditContract cc : clientCredits) {
            contractIds.add(cc.getId());
        }
        java.util.List<Payment> filteredPayments = new java.util.ArrayList<>();
        for (Payment payment : paymentsModel.getAll()) {
            if (contractIds.contains(payment.getCreditContractId())) {
                filteredPayments.add(payment);
            }
        }
        view.setPayments(filteredPayments);
    }


    private void onPay() {
        CreditContract contract = view.getContractCombo().getValue();
        if (contract == null) {
            view.showMessage("Выберите договор");
            return;
        }
        double amount;
        try {
            amount = Double.parseDouble(view.getAmountField().getText().trim());
        } catch (NumberFormatException ex) {
            view.showMessage("Неверная сумма");
            return;
        }
        LocalDate date = view.getDatePicker().getValue();
        if (date == null) {
            view.showMessage("Выберите дату");
            return;
        }
        try {
            Database.insertPayment(contract.getId(), amount, date);
            paymentsModel.add(new Payment(contract.getId(), amount, 0.0, date));
            view.showMessage("Платёж успешно проведён");
            view.getAmountField().clear();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, "Ошибка при платеже: " + ex.getMessage()).showAndWait();
        }
    }
}
