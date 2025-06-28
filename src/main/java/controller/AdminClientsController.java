package controller;

import model.Client;
import model.Clients;
import view.AdminClientsView;
import database.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminClientsController {
    private final AdminClientsView view;
    private final Clients model;
    private final TableView<Client> table;

    public AdminClientsController(AdminClientsView view, Clients model) {
        this.view = view;
        this.model = model;
        this.table = view.getClients();
        table.setEditable(true);

        TableColumn<Client, String> nameCol = (TableColumn<Client, String>) table.getColumns().get(1);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setName(event.getNewValue());
            saveClient(client);
        });

        TableColumn<Client, String> propertyCol = (TableColumn<Client, String>) table.getColumns().get(2);
        propertyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        propertyCol.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setPropertyType(event.getNewValue());
            saveClient(client);
        });

        TableColumn<Client, String> addressCol = (TableColumn<Client, String>) table.getColumns().get(3);
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setAddress(event.getNewValue());
            saveClient(client);
        });

        model.addListener(this::reloadTable);
        reloadTable();
    }

    private void reloadTable() {
        table.getItems().setAll(model.getAll());
    }

    private void saveClient(Client client) {
        try {
            Database.updateClient(client);
            List<Client> list = new ArrayList<>(model.getAll());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == client.getId()) {
                    list.set(i, client);
                    break;
                }
            }
            model.setAll(list);
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR,
                    "Не удалось сохранить изменения: " + ex.getMessage()
            ).showAndWait();
        }
    }
}
