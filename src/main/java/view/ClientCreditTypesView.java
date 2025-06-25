package view;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientCreditTypesView {
    private TableView<model.CreditType> types = new TableView<>();

    public ClientCreditTypesView() {

        TableColumn <model.CreditType, String> name = new TableColumn<>("Название");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn <model.CreditType, String> conditions = new TableColumn<>("Условия");
        conditions.setCellValueFactory(new PropertyValueFactory<>("conditions"));

        TableColumn <model.CreditType, Double> percent = new TableColumn<>("Процент");
        percent.setCellValueFactory(new PropertyValueFactory<>("percent"));

        TableColumn <model.CreditType, Integer> returnPeriod = new TableColumn<>("Срок (в месяцах)");
        returnPeriod.setCellValueFactory(new PropertyValueFactory<>("returnPeriod"));

        types.getColumns().addAll(name, conditions, percent, returnPeriod);
    }

    public Node getType() {
        return types;
    }

    public TableView <model.CreditType> getTypes() {
        return types;
    }
}
