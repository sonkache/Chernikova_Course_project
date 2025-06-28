package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AdminMainView {
    private final TabPane tabPane = new TabPane();
    private final Tab clientsTab = new Tab("Список клиентов");
    private final Tab typesTab = new Tab("Виды кредитов");
    private final Tab issueTab = new Tab("Выдача кредита");
    private final Tab historyTab = new Tab("История платежей");

    private final AdminClientsView clientsView = new AdminClientsView();
    private final AdminCreditTypesView typesView = new AdminCreditTypesView();
    private final AdminIssueCreditView issueView = new AdminIssueCreditView();
    private final AdminPaymentHistoryView historyView = new AdminPaymentHistoryView();

    public void start(Stage stage) {
        clientsTab.setContent(clientsView.getClient());
        typesTab.setContent(typesView.getCredit());
        issueTab.setContent(issueView.getCredit());
        historyTab.setContent(historyView.getHistory());
        String tabHeaderStyle = "-fx-background-color: white; -fx-text-fill: #0277bd; -fx-font-size: 16px; -fx-font-style: italic; -fx-border-color: #0277bd; -fx-border-style: dashed;";

        for (Tab t : new Tab[]{clientsTab, typesTab, issueTab, historyTab}) {
            t.setStyle(tabHeaderStyle);
            Region content = (Region) t.getContent();
            content.setPadding(new Insets(20));
            content.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #ffffff);");
        }

        tabPane.getTabs().addAll(clientsTab, typesTab, issueTab, historyTab);
        tabPane.setStyle("-fx-background-color: #e1f5fe;");

        BorderPane root = new BorderPane(tabPane);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #ffffff);");
        Scene scene = new Scene(root, 700, 600);
        stage.setTitle("Личный кабинет администратора");
        stage.setScene(scene);
        stage.show();
    }

    public AdminClientsView getClientsView(){ return clientsView; }
    public AdminCreditTypesView getCreditTypesView(){ return typesView; }
    public AdminIssueCreditView getIssueCreditView(){ return issueView; }
    public AdminPaymentHistoryView getHistoryView(){ return historyView; }
}
