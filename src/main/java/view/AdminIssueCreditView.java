package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import model.Client;
import model.CreditType;

public class AdminIssueCreditView {
    private final ComboBox<Client> client = new ComboBox<>();
    private final ComboBox<CreditType> type = new ComboBox<>();
    private final TextField amount = new TextField();
    private final DatePicker issueDatePicker = new DatePicker();
    private final DatePicker returnDatePicker = new DatePicker();
    private final Button button = new Button("Выдать кредит");
    private final GridPane credit = create();

    private GridPane create() {
        GridPane p = new GridPane();
        p.setVgap(8); p.setHgap(10); p.setPadding(new Insets(20));
        p.addRow(0, new Label("Клиент:"), client);
        p.addRow(1, new Label("Вид кредита:"), type);
        p.addRow(2, new Label("Сумма:"), amount);
        p.addRow(3, new Label("Дата выдачи:"), issueDatePicker);
        p.addRow(4, new Label("Дата возврата:"), returnDatePicker);
        p.add(button, 1, 5);
        return p;
    }

    public Node getCredit(){return credit;}
    public ComboBox<Client> getClient(){return client;}
    public ComboBox<CreditType> getType(){return type;}
    public TextField getAmount(){return amount;}
    public DatePicker getIssueDatePicker(){return issueDatePicker;}
    public DatePicker getReturnDatePicker(){return returnDatePicker;}
    public Button getButton(){return button;}

    public void showMessage(String msg){ new Alert(Alert.AlertType.INFORMATION, msg).showAndWait(); }
    public void showError  (String msg){ new Alert(Alert.AlertType.ERROR, msg).showAndWait(); }
}
