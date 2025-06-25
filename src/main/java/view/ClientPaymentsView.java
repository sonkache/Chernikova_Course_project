package view;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientPaymentsView {
    private TableView <model.Payment> payments = new TableView<>();

    public ClientPaymentsView() {
        TableColumn <model.Payment, Integer> credit = new TableColumn<>("Номер кредитного договора");
        credit.setCellValueFactory(new PropertyValueFactory<>("creditContractId"));

        TableColumn <model.Payment, Double> amount = new TableColumn<>("Сумма");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn <model.Payment, Double> penalty = new TableColumn<>("Штраф");
        penalty.setCellValueFactory(new PropertyValueFactory<>("penalty"));

        TableColumn <model.Payment, String> date = new TableColumn<>("Дата");
        date.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        payments.getColumns().addAll(credit, amount, penalty, date);
    }

    public Node getPayment(){
        return payments;
    }

    public TableView<model.Payment> getPayments(){
        return payments;
    }
}
