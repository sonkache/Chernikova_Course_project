package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Payment;

public class AdminPaymentHistoryView {
    private ComboBox<Integer> contract = new ComboBox<>();
    private TableView<Payment> table = new TableView<>();
    private VBox history;

    public AdminPaymentHistoryView() {
        TableColumn<Payment, Integer> creditContractId = new TableColumn<>("Номер кредитного договора");
        creditContractId.setCellValueFactory(new PropertyValueFactory<>("creditContractId"));

        TableColumn<Payment, Double> amount = new TableColumn<>("Сумма");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Payment, Double> penalty = new TableColumn<>("Штраф");
        penalty.setCellValueFactory(new PropertyValueFactory<>("penalty"));

        TableColumn<Payment, String> paymentDate = new TableColumn<>("Дата");
        paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        table.getColumns().addAll(creditContractId, amount, penalty, paymentDate);
        history = new VBox(10, new Label("Выберите договор:"), contract, table);
        history.setPadding(new Insets(20));
    }


    public Node getHistory() {
        return history;
    }

    public ComboBox<Integer> getContract() {
        return contract;
    }

    public TableView<Payment> getTable() {
        return table;
    }
}
