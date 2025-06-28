package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.CreditContract;
import model.Payment;
import java.time.LocalDate;
import java.util.List;

public class ClientPaymentsView {
    private final TableView<model.Payment> paymentsTable = new TableView<>();
    private final ComboBox<CreditContract> contractCombo = new ComboBox<>();
    private final TextField amountField = new TextField();
    private final DatePicker datePicker = new DatePicker(LocalDate.now());
    private final Button payButton = new Button("Внести платёж");
    private final VBox root;

    public ClientPaymentsView() {
        TableColumn<model.Payment, Integer> credit = new TableColumn<>("Номер кредитного договора");
        credit.setCellValueFactory(new PropertyValueFactory<>("creditContractId"));

        TableColumn<model.Payment, Double> amount = new TableColumn<>("Сумма");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<model.Payment, Double> penalty = new TableColumn<>("Штраф");
        penalty.setCellValueFactory(new PropertyValueFactory<>("penalty"));

        TableColumn<model.Payment, LocalDate> date = new TableColumn<>("Дата");
        date.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        paymentsTable.getColumns().addAll(credit, amount, penalty, date);

        contractCombo.setPromptText("Выберите договор");
        amountField.setPromptText("Сумма платежа");

        HBox controls = new HBox(8, new Label("Договор:"), contractCombo, new Label("Сумма:"), amountField, new Label("Дата:"), datePicker, payButton);
        controls.setPadding(new Insets(10));
        HBox.setHgrow(amountField, Priority.ALWAYS);

        root = new VBox(10, paymentsTable, controls);
        root.setPadding(new Insets(10));
    }

    public Parent getPayment() {
        return root;
    }

    public TableView<model.Payment> getPaymentsTable() { return paymentsTable; }
    public ComboBox<CreditContract> getContractCombo() { return contractCombo; }
    public TextField getAmountField() { return amountField; }
    public DatePicker getDatePicker() { return datePicker; }
    public Button getPayButton() { return payButton; }

    public void showMessage(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    public void setPayments(List<Payment> payments) {
        paymentsTable.getItems().setAll(payments);
    }
}
