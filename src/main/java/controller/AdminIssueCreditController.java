package controller;

import database.Database;
import model.*;
import view.AdminIssueCreditView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AdminIssueCreditController {
    private final AdminIssueCreditView view;
    private final Clients clientsModel;
    private final CreditTypes typesModel;
    private final CreditContracts contractsModel;
    public AdminIssueCreditController(AdminIssueCreditView view, Clients clientsModel, CreditTypes typesModel, CreditContracts contractsModel) {
        this.view = view;
        this.clientsModel = clientsModel;
        this.typesModel = typesModel;
        this.contractsModel = contractsModel;

        view.getClient().setItems(FXCollections.observableArrayList(clientsModel.getAll()));
        view.getType().setItems(FXCollections.observableArrayList(typesModel.getAll()));

        clientsModel.addListener(new Runnable() {
            @Override
            public void run() {
                refreshClients();
            }
        });

        typesModel.addListener(new Runnable() {
            @Override
            public void run() {
                refreshTypes();
            }
        });

        view.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onIssue();  // Вызов метода для выдачи кредита
            }
        });

        refreshClients();
        refreshTypes();
    }

    private void refreshClients() {
        view.getClient().setItems(FXCollections.observableArrayList(clientsModel.getAll()));
    }

    private void refreshTypes() {
        view.getType().setItems(FXCollections.observableArrayList(typesModel.getAll()));
    }

    private void onIssue() {
        Client client = view.getClient().getValue();
        CreditType type = view.getType().getValue();
        String amtStr = view.getAmount().getText().trim();
        String issueStr = view.getIssueDate().getText().trim();
        String returnStr = view.getReturnDate().getText().trim();


        if (client == null || type == null || amtStr.isEmpty() || issueStr.isEmpty() || returnStr.isEmpty()) {
            return;
        }

        double amount;
        LocalDate issueDate, returnDate;

        try {
            amount = Double.parseDouble(amtStr);
            issueDate = LocalDate.parse(issueStr);
            returnDate = LocalDate.parse(returnStr);
        } catch (NumberFormatException | DateTimeParseException ex) {
            return;
        }
        String sql = """
            INSERT INTO credit_contracts
              (client_id, credit_type_id, amount, issue_date, return_date)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, client.getId());
            ps.setInt(2, type.getId());
            ps.setDouble(3, amount);
            ps.setDate(4, Date.valueOf(issueDate));
            ps.setDate(5, Date.valueOf(returnDate));
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    CreditContract cc = new CreditContract(
                            newId,
                            client.getId(),
                            type.getId(),
                            amount,
                            issueDate,
                            returnDate
                    );
                    contractsModel.add(cc);
                }
            }
            view.getAmount().clear();
            view.getIssueDate().clear();
            view.getReturnDate().clear();

        } catch (SQLException ex) {
            ;
        }
    }
}
