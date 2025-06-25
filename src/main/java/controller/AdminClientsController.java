package controller;

import model.Clients;
import view.AdminClientsView;
import javafx.collections.FXCollections;

public class AdminClientsController {
    private final AdminClientsView view;
    private final Clients model;

    public AdminClientsController(AdminClientsView view, Clients model) {
        this.view  = view;
        this.model = model;
        update();
        model.addListener(this::update);
    }

    private void update() {
        var list = model.getAll();
        view.getClients().setItems(FXCollections.observableArrayList(list));
    }
}
