package view;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminMainView {
    private TabPane tabPane = new TabPane();
    private Tab clients = new Tab("Список клиентов");
    private Tab types = new Tab("Виды кредитов");
    private Tab issue = new Tab("Выдача кредита");
    private Tab history = new Tab("История платежей клиентов");

    private AdminClientsView clientsView = new AdminClientsView();
    private AdminCreditTypesView typesView = new AdminCreditTypesView();
    private AdminIssueCreditView issueView = new AdminIssueCreditView();
    private AdminPaymentHistoryView historyView = new AdminPaymentHistoryView();

    public void start(Stage stage){
        clients.setContent(clientsView.getClient());
        types.setContent(typesView.getCredit());
        issue.setContent(issueView.getCredit());
        history.setContent(historyView.getHistory());

        tabPane.getTabs().addAll(clients, types, issue, history);
        BorderPane panel = new BorderPane(tabPane);
        stage.setTitle("Личный кабинет администратора");
        stage.setScene(new Scene(panel, 400, 500));
        stage.show();
    }

    public AdminClientsView getClientsView(){
        return clientsView;
    }

    public AdminCreditTypesView getCreditTypesView(){
        return typesView;
    }

    public AdminIssueCreditView getIssueCreditView(){
        return issueView;
    }

    public AdminPaymentHistoryView getHistoryView(){
        return historyView;
    }
}
