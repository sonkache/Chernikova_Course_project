//package view;
//
//import javafx.scene.Node;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//public class ClientCreditsView {
//    private TableView<model.CreditContract> credits = new TableView<>();
//
//    public ClientCreditsView() {
//        TableColumn<model.CreditContract, Integer> id = new TableColumn<>("Номер кредитного договора");
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn<model.CreditContract, Double> amount = new TableColumn<>("Сумма");
//        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//
//        TableColumn<model.CreditContract, String> issueDate = new TableColumn<>("Дата выдачи");
//        issueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
//
//        TableColumn<model.CreditContract, String> returnDate = new TableColumn<>("Дата возврата");
//        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
//
//        credits.getColumns().addAll(id, amount, issueDate, returnDate);
//    }
//
//    public Node getCredit(){
//        return credits;
//    }
//
//    public TableView<model.CreditContract> getCredits(){
//        return credits;
//    }
//
//
//}
package view;

import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.CreditContract;
import java.time.LocalDate;
import java.util.List;

public class ClientCreditsView {
    private final TableView<CreditContract> creditsTable = new TableView<>();
    private final VBox root;

    public ClientCreditsView() {
        TableColumn<CreditContract, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<CreditContract, Double> amountCol = new TableColumn<>("Сумма");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<CreditContract, LocalDate> issueDateCol = new TableColumn<>("Дата выдачи");
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        TableColumn<CreditContract, LocalDate> returnDateCol = new TableColumn<>("Дата возврата");
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        creditsTable.getColumns().addAll(idCol, amountCol, issueDateCol, returnDateCol);
        root = new VBox(creditsTable);
    }

    public Parent getCredit() {
        return root;
    }

    public TableView<CreditContract> getCreditsTable() {
        return creditsTable;
    }

    public void setCredits(List<CreditContract> contracts) {
        creditsTable.getItems().setAll(contracts);
    }
}
