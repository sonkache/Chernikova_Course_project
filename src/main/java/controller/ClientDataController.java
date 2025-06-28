package controller;

import model.Client;
import model.Clients;
import view.ClientDataView;
import database.Database;
import javafx.scene.control.Alert;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDataController {
    private final ClientDataView view;
    private final Clients model;
    private final int clientId;

    public ClientDataController(ClientDataView view, Clients model, int clientId) {
        this.view = view;
        this.model = model;
        this.clientId = clientId;
        view.getSaveButton().setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                onSave();
            }
        });
        model.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
        update();
    }

    private void update() {
        Client client = null;
        for (Client c : model.getAll()) {
            if (c.getId() == clientId) {
                client = c;
                break;
            }
        }
        if (client == null){
            return;
        }
        view.setName(client.getName());
        view.setProperty(client.getPropertyType());
        view.setAddress(client.getAddress());
        view.setPhone(client.getPhoneNumber());
        view.setContact(client.getContactPerson());
    }

    private void onSave() {
        String name = view.getName().trim();
        String property = view.getProperty().trim();
        String address = view.getAddress().trim();
        String phone = view.getPhone().trim();
        String contact = view.getContact().trim();
        Client updated = new Client(clientId, name, property, address, phone, contact);

        try {
            Database.updateClient(updated);
            List<Client> all = new ArrayList<>(model.getAll());
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getId() == clientId) {
                    all.set(i, updated);
                    break;
                }
            }
            model.setAll(all);

            view.showMessage("Данные успешно сохранены");
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, "Ошибка сохранения: " + ex.getMessage())
                    .showAndWait();
        }
    }
}
