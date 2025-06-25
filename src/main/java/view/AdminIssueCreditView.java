package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Client;
import model.CreditType;

public class AdminIssueCreditView {
    private final ComboBox<Client> client = new ComboBox<>();
    private final ComboBox<CreditType> type = new ComboBox<>();
    private final TextField amount = new TextField();
    private final TextField issueDate = new TextField();
    private final TextField returnDate = new TextField();
    private final Button button = new Button("Выдать кредит");
    private final GridPane credit = create();

    private GridPane create() {
        GridPane panel = new GridPane();
        panel.setVgap(8);
        panel.setHgap(10);
        panel.setPadding(new Insets(20));

        panel.addRow(0, new Label("Клиент:"), client);
        panel.addRow(1, new Label("Вид кредита:"), type);
        panel.addRow(2, new Label("Сумма:"), amount);
        panel.addRow(3, new Label("Дата выдачи (ГГГГ-MM-ДД):"), issueDate);
        panel.addRow(4, new Label("Дата возврата (ГГГГ-MM-ДД):"), returnDate);
        panel.add(button, 1, 5);
        return panel;
    }

    public Node getCredit(){
        return credit;
    }

    public ComboBox<Client> getClient(){
        return client;
    }

    public ComboBox<CreditType> getType(){
        return type;
    }

    public TextField getAmount(){
        return amount;
    }

    public TextField getIssueDate(){
        return issueDate;
    }

    public TextField getReturnDate(){
        return returnDate;
    }

    public Button getButton(){
        return button;
    }
}
