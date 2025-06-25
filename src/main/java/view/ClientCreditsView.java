package view;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientCreditsView {
    private TableView <model.CreditContract> credits = new TableView<>();

    public ClientCreditsView() {
        TableColumn<model.CreditContract, Integer> id = new TableColumn<>("Номер кредитного договора");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<model.CreditContract, Double> amount = new TableColumn<>("Сумма");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<model.CreditContract, String> issueDate = new TableColumn<>("Дата выдачи");
        issueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        TableColumn<model.CreditContract, String> returnDate = new TableColumn<>("Дата возврата");
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        credits.getColumns().addAll(id, amount, issueDate, returnDate);
    }

    public Node getCredit(){
        return credits;
    }

    public TableView<model.CreditContract> getCredits(){
        return credits;
    }
}
