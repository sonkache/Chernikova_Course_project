package view;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientMainView {
    private TabPane tabPane = new TabPane();
    private Tab data = new Tab("Ваши данные");
    private Tab credits = new Tab("Ваши кредиты");
    private Tab pays = new Tab("Ваши платежи");
    private Tab types = new Tab("Виды кредитов");

    private ClientDataView dataView = new ClientDataView();
    private ClientCreditsView creditsView = new ClientCreditsView();
    private ClientPaymentsView paymentsView = new ClientPaymentsView();
    private ClientCreditTypesView typesView = new ClientCreditTypesView();

    public void start(Stage stage) {
        data.setContent(dataView.getData());
        credits.setContent(creditsView.getCredit());
        pays.setContent(paymentsView.getPayment());
        types.setContent(typesView.getType());

        tabPane.getTabs().addAll(data, credits, pays, types);
        BorderPane panel = new BorderPane(tabPane);
        stage.setTitle("Личный кабинет");
        stage.setScene(new Scene(panel, 600, 600));
        stage.show();
    }

    public ClientDataView getDataView(){
        return dataView;
    }

    public ClientCreditsView getCreditsView(){
        return creditsView;
    }

    public ClientPaymentsView getPaymentsView(){
        return paymentsView;
    }

    public ClientCreditTypesView getTypesView(){
        return typesView;
    }
}
