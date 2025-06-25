package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.CreditType;

public class AdminCreditTypesView {
    private final TableView<CreditType> types = new TableView<>();
    private final TextField name = new TextField();
    private final TextField conditions = new TextField();
    private final TextField percent = new TextField();
    private final TextField period = new TextField();
    private final Button addButton = new Button("Добавить вид кредита");
    private final VBox credit;

    public AdminCreditTypesView() {
        TableColumn<CreditType, String> name = new TableColumn<>("Название");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CreditType, String> conditions = new TableColumn<>("Условия");
        conditions.setCellValueFactory(new PropertyValueFactory<>("conditions"));

        TableColumn<CreditType, Double> percent = new TableColumn<>("Процент");
        percent.setCellValueFactory(new PropertyValueFactory<>("percent"));

        TableColumn<CreditType, Integer> period = new TableColumn<>("Срок (в месяцах)");
        period.setCellValueFactory(new PropertyValueFactory<>("returnPeriod"));
        types.getColumns().addAll(name, conditions, percent, period);

        VBox form = new VBox(10, new Label("Название:"), this.name, new Label("Условия:"), this.conditions, new Label("Процент:"), this.percent, new Label("Срок (в месяцах):"), this.period, addButton);
        form.setPadding(new Insets(10));
        credit = new VBox(10, types, form);
        credit.setPadding(new Insets(10));
    }

    public Node getCredit(){
        return credit;
    }

    public TableView<CreditType> getTypes(){
        return types;
    }

    public TextField getName(){
        return name;
    }

    public TextField getConditions(){
        return conditions;
    }

    public TextField getPercent(){
        return percent;
    }

    public TextField getPeriod(){
        return period;
    }

    public Button getAddButton(){
        return addButton;
    }
}
