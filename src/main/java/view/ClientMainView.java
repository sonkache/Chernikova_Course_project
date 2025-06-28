package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ClientMainView {
    private final TabPane tabPane = new TabPane();
    private final Tab dataTab = new Tab("Ваши данные");
    private final Tab issueTab = new Tab("Оформить кредит");
    private final Tab creditsTab = new Tab("Ваши кредиты");
    private final Tab paysTab = new Tab("Ваши платежи");
    private final Tab typesTab = new Tab("Виды кредитов");

    private final ClientDataView dataView = new ClientDataView();
    private final AdminIssueCreditView issueView = new AdminIssueCreditView();
    private final ClientCreditsView creditsView = new ClientCreditsView();
    private final ClientPaymentsView paymentsView = new ClientPaymentsView();
    private final ClientCreditTypesView typesView = new ClientCreditTypesView();

    public void start(Stage stage) {
        dataTab.setContent(dataView.getData());
        issueTab.setContent(issueView.getCredit());
        creditsTab.setContent(creditsView.getCredit());
        paysTab.setContent(paymentsView.getPayment());
        typesTab.setContent(typesView.getType());

        String tabHeaderStyle = " -fx-background-color: white; -fx-text-fill: #0277bd; -fx-font-size: 16px; -fx-font-style: italic; -fx-border-color: #0277bd; -fx-border-style: dashed;";

        for (Tab t : new Tab[]{dataTab, issueTab, creditsTab, paysTab, typesTab}) {
            t.setStyle(tabHeaderStyle);
            Region content = (Region) t.getContent();
            content.setPadding(new Insets(20));
            content.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #ffffff);");
        }

        tabPane.getTabs().addAll(dataTab, issueTab, creditsTab, paysTab, typesTab);
        tabPane.setStyle("-fx-background-color: #e1f5fe;");

        BorderPane root = new BorderPane(tabPane);
        root.setPadding(new Insets(18));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #ffffff);");

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Личный кабинет клиента");
        stage.setScene(scene);
        stage.show();
    }

    public ClientDataView getDataView(){ return dataView; }
    public AdminIssueCreditView getIssueCreditView(){ return issueView; }
    public ClientCreditsView getCreditsView(){ return creditsView; }
    public ClientPaymentsView getPaymentsView(){ return paymentsView; }
    public ClientCreditTypesView getTypesView(){ return typesView; }
}
