package controller;

import model.*;
import view.AdminPaymentHistoryView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;
import java.util.stream.Collectors;

public class AdminPaymentHistoryController {
    private final AdminPaymentHistoryView view;
    private final CreditContracts contractsModel;
    private final Payments paymentsModel;

    public AdminPaymentHistoryController(AdminPaymentHistoryView view, CreditContracts contractsModel, Payments paymentsModel) {
        this.view = view;
        this.contractsModel = contractsModel;
        this.paymentsModel = paymentsModel;
        refreshContracts();

        contractsModel.addListener(new Runnable() {
            @Override
            public void run() {
                refreshContracts();
            }
        });
        view.getContract().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshPayments();
            }
        });
        paymentsModel.addListener(new Runnable() {
            @Override
            public void run() {
                refreshPayments();
            }
        });
    }
    private void refreshContracts() {
        List<Integer> ids = contractsModel.getAll().stream()
                .map(new java.util.function.Function<CreditContract, Integer>() {
                    @Override
                    public Integer apply(CreditContract contract) {
                        return contract.getId();
                    }
                })
                .collect(Collectors.toList());
        view.getContract().setItems(FXCollections.observableArrayList(ids));
    }

    private void refreshPayments() {
        Integer selected = view.getContract().getValue();
        if (selected == null) return;
        List<Payment> list = paymentsModel.getAll().stream()
                .filter(new java.util.function.Predicate<Payment>() {
                    @Override
                    public boolean test(Payment p) {
                        return p.getCreditContractId() == selected;
                    }
                })
                .collect(Collectors.toList());
        view.getTable().setItems(FXCollections.observableArrayList(list));
    }
}
